package com.ephirium.coffee.databases.compliments.datastore

import com.ephirium.coffee.common.Status.*
import com.ephirium.coffee.common.onTimeout
import com.ephirium.coffee.common.runTimeout
import com.ephirium.coffee.databases.compliments.config.database
import com.ephirium.coffee.databases.compliments.config.timeout
import com.ephirium.coffee.databases.compliments.models.ComplimentDTO
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

internal class ComplimentsDataStoreImpl : ComplimentsDataStore {
    override suspend fun getCompliments() = flow {
        runTimeout(timeout) {
            database.addSnapshotListener { snapshot, exception ->
                CoroutineScope(coroutineContext).launch {
                    exception?.let {
                        emit(NetworkError)
                        return@launch
                    }
                    
                    if (snapshot == null) {
                        emit(Error)
                        return@launch
                    }
                    
                    val compliments = snapshot.toObjects<ComplimentDTO>()
                    if (compliments.isEmpty()) {
                        emit(Error)
                        return@launch
                    }
                    emit(Success(compliments))
                }
            }
        }.onTimeout {
            emit(TimeoutError)
        }
    }.flowOn(Dispatchers.IO)
    
    override suspend fun getComplimentById(id: String) = flow {
        runTimeout(timeout) {
            database.document(id).addSnapshotListener { snapshot, exception ->
                CoroutineScope(coroutineContext).launch {
                    exception?.let {
                        emit(NetworkError)
                        return@launch
                    }
                    
                    if (snapshot == null) {
                        emit(NetworkError)
                        return@launch
                    }
                    
                    if (!snapshot.exists()) {
                        emit(Error)
                        return@launch
                    }
                    
                    emit(
                        when (val compliment = snapshot.toObject<ComplimentDTO>()) {
                            null -> Error
                            else -> Success(compliment)
                        }
                    )
                }
            }
        }.onTimeout {
            emit(TimeoutError)
        }
    }.flowOn(Dispatchers.IO)
    
    override suspend fun createCompliment(compliment: ComplimentDTO) = flow {
        runTimeout(timeout) {
            database.document(compliment.id).set(
                mapOf(
                    "text" to compliment.text,
                    "user" to compliment.user,
                )
            ).addOnSuccessListener {
                CoroutineScope(coroutineContext).launch {
                    emit(Success(Unit))
                }
            }.addOnFailureListener {
                CoroutineScope(coroutineContext).launch {
                    emit(
                        when (it) {
                            is FirebaseFirestoreException -> NetworkError
                            else                          -> Error
                        }
                    )
                }
            }
        }.onTimeout {
            emit(TimeoutError)
        }
    }.flowOn(Dispatchers.IO)
    
    override suspend fun updateCompliment(compliment: ComplimentDTO) = flow {
        runTimeout(timeout) {
            database.document(compliment.id).update(
                mapOf(
                    "text" to compliment.text,
                    "user" to compliment.user,
                )
            ).addOnSuccessListener {
                CoroutineScope(coroutineContext).launch {
                    emit(Success(Unit))
                }
            }.addOnFailureListener {
                CoroutineScope(coroutineContext).launch {
                    emit(
                        when (it) {
                            is FirebaseFirestoreException -> NetworkError
                            else                          -> Error
                        }
                    )
                }
            }
        }.onTimeout {
            emit(TimeoutError)
        }
    }.flowOn(Dispatchers.IO)
    
    override suspend fun deleteCompliment(compliment: ComplimentDTO) = flow {
        runTimeout(timeout) {
            database.document(compliment.id).delete().addOnSuccessListener {
                CoroutineScope(coroutineContext).launch {
                    emit(Success(Unit))
                }
            }.addOnFailureListener {
                CoroutineScope(coroutineContext).launch {
                    emit(
                        when (it) {
                            is FirebaseFirestoreException -> NetworkError
                            else                          -> Error
                        }
                    )
                }
            }
        }.onTimeout {
            emit(TimeoutError)
        }
    }.flowOn(Dispatchers.IO)
}