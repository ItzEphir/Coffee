package com.ephirium.coffee.data.repository

import com.ephirium.coffee.data.model.ComplimentDto
import com.ephirium.coffee.data.storage.Database
import com.ephirium.coffee.domain.model.dto.ComplimentDtoBase
import com.ephirium.coffee.domain.repository.ComplimentRepositoryBase
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

internal class ComplimentRepositoryImpl : ComplimentRepositoryBase {
    private val compliments = Database.compliments
    
    override suspend fun getComplimentsFlow(): Flow<Result<ComplimentDtoBase>> = callbackFlow {
        val registration = compliments.addSnapshotListener { snapshot, e ->
            e?.let {
                close(it)
            }
            try {
                snapshot!!.documents.forEach { document ->
                    val res = document.toObject<ComplimentDto>()!!
                    trySend(Result.success(res))
                }
            } catch (e: Exception) {
                close(e)
            }
        }
        
        awaitClose(registration::remove)
        
        invokeOnClose {
            it?.let { throwable -> trySend(Result.failure(throwable)) }
        }
    }
    
    override suspend fun getComplimentFlowById(id: String): Flow<Result<ComplimentDtoBase>> =
        callbackFlow {
            val registration = compliments.document(id).addSnapshotListener { snapshot, e ->
                e?.let {
                    close(it)
                }
                try {
                    val res = snapshot!!.toObject<ComplimentDto>()!!
                    trySend(Result.success(res))
                } catch (e: Exception) {
                    trySend(Result.failure(e))
                }
            }
            
            awaitClose(registration::remove)
            
            invokeOnClose {
                it?.let { throwable -> trySend(Result.failure(throwable)) }
            }
        }
}