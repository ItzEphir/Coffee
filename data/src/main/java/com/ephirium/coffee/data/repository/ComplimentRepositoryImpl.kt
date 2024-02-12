package com.ephirium.coffee.data.repository

import com.ephirium.coffee.data.datastore.ComplimentId
import com.ephirium.coffee.data.model.ComplimentDTO
import com.ephirium.coffee.data.storage.Database
import com.ephirium.coffee.domain.model.dto.ComplimentDTOBase
import com.ephirium.coffee.domain.repository.ComplimentRepositoryBase
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.asFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class ComplimentRepositoryImpl() : ComplimentRepositoryBase {
    
    override suspend fun getCompliments(): Flow<Result<List<ComplimentDTOBase>>> = callbackFlow {
        val listenerRegistration = Database.compliments.addSnapshotListener { snapshot, exception ->
            exception?.let {
                cancel(CancellationException(it))
            }
            CoroutineScope(coroutineContext).launch {
                send(
                    when (snapshot) {
                        null -> Result.failure(
                            FirebaseFirestoreException(
                                "Snapshot for compliments wasn't found",
                                FirebaseFirestoreException.Code.NOT_FOUND
                            )
                        )
                        
                        else -> Result.success(snapshot.toObjects<ComplimentDTO>())
                    }
                )
            }
        }
        
        awaitClose(listenerRegistration::remove)
    }.flowOn(Dispatchers.IO)
    
    override suspend fun getComplimentById(id: String): Flow<Result<ComplimentDTOBase>> =
        callbackFlow<Result<ComplimentDTOBase>> {
            val registration =
                Database.compliments.document(id).addSnapshotListener { snapshot, e ->
                    launch {
                        e?.let {
                            close(it)
                        }
                        if (snapshot == null) {
                            close(NullPointerException("Document Snapshot is null"))
                            return@launch
                        }
                        if (!snapshot.exists()) {
                            close(
                                FirebaseFirestoreException(
                                    "Snapshot does not exist",
                                    FirebaseFirestoreException.Code.UNAVAILABLE
                                )
                            )
                            return@launch
                        }
                        val dto = snapshot.toObject<ComplimentDTO>()
                        if (dto == null) {
                            send(Result.failure(NullPointerException("Document can`t be parsed to dto")))
                            return@launch
                        }
                        send(Result.success(dto))
                    }
                }
            
            awaitClose(registration::remove)
            
            invokeOnClose {
                it?.let { throwable -> trySend(Result.failure(throwable)) }
            }
        }.flowOn(Dispatchers.IO)
    
    override suspend fun getSavedComplimentIdLocally() =
        Database.realm.query(ComplimentId::class).asFlow().map {
            when(val complimentId = it.list.lastOrNull()){
                null -> Result.failure(NullPointerException("Compliment id was not stored"))
                else -> Result.success(complimentId.complimentId)
            }
        }.flowOn(Dispatchers.IO)
    
    override suspend fun saveComplimentIdLocally(id: String) = Database.realm.write {
        copyToRealm(ComplimentId().apply { complimentId = id }, UpdatePolicy.ALL)
    }.asFlow().map {
        when (val complimentId = it.obj) {
            null -> Result.failure(NullPointerException("Compliment id was not stored"))
            else -> Result.success(complimentId.complimentId)
        }
    }.flowOn(Dispatchers.IO)
}