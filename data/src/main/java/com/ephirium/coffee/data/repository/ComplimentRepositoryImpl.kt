package com.ephirium.coffee.data.repository

import com.ephirium.coffee.data.model.ComplimentDTO
import com.ephirium.coffee.data.storage.Database
import com.ephirium.coffee.domain.model.dto.ComplimentDTOBase
import com.ephirium.coffee.domain.repository.ComplimentRepositoryBase
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.cancellation.CancellationException

internal class ComplimentRepositoryImpl : ComplimentRepositoryBase {
    private val compliments = Database.compliments
    
    override suspend fun getComplimentsFlow(): Flow<Result<List<ComplimentDTOBase>>> =
        callbackFlow {
            val listenerRegistration = compliments.addSnapshotListener { snapshot, exception ->
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
    
    override suspend fun getComplimentFlowById(id: String): Flow<Result<ComplimentDTOBase>> =
        callbackFlow<Result<ComplimentDTOBase>> {
            val registration = compliments.document(id).addSnapshotListener { snapshot, e ->
                launch {
                    e?.let {
                        close(it)
                    }
                    if (snapshot == null) {
                        send(Result.failure(NullPointerException("Document Snapshot is null")))
                        return@launch
                    }
                    if (!snapshot.exists()) {
                        send(
                            Result.failure(
                                FirebaseFirestoreException(
                                    "Snapshot does not exist",
                                    FirebaseFirestoreException.Code.UNAVAILABLE
                                )
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
}