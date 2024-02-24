package com.ephirium.coffee.databases.users.datastore

import com.ephirium.coffee.common.Status.*
import com.ephirium.coffee.common.runTimeout
import com.ephirium.coffee.databases.users.config.database
import com.ephirium.coffee.databases.users.config.timeout
import com.ephirium.coffee.databases.users.models.UserDTO
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

internal class UserDataStoreImpl : UserDataStore {
    
    override suspend fun getUsers() = flow {
        val coroutineContext = currentCoroutineContext()
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
                    
                    val users = snapshot.toObjects<UserDTO>()
                    if (users.isEmpty()) {
                        emit(Error)
                        return@launch
                    }
                    
                    emit(Success(users))
                }
            }
        }.onFailure {
            emit(TimeoutError)
        }
    }.flowOn(Dispatchers.IO)
    
    override suspend fun getUserById(id: String) = flow {
        val coroutineContext = currentCoroutineContext()
        runTimeout(timeout) {
            database.document(id).addSnapshotListener { snapshot, exception ->
                CoroutineScope(coroutineContext).launch {
                    exception?.let {
                        emit(NetworkError)
                        return@launch
                    }
                    
                    if (snapshot == null) {
                        emit(Error)
                        return@launch
                    }
                    
                    val user = snapshot.toObject<UserDTO>()
                    if (user == null) {
                        emit(Error)
                        return@launch
                    }
                    
                    emit(Success(user))
                }
            }
        }.onFailure {
            emit(TimeoutError)
        }
    }.flowOn(Dispatchers.IO)
    
    override suspend fun createUser(user: UserDTO) = flow {
        val coroutineContext = currentCoroutineContext()
        runTimeout(timeout) {
            database.document(user.id).set(
                mapOf(
                    "devices" to user.devices,
                    "user" to user.user,
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
        }.onFailure {
            emit(TimeoutError)
        }
    }.flowOn(Dispatchers.IO)
    
    override suspend fun updateUser(user: UserDTO) = flow {
        val coroutineContext = currentCoroutineContext()
        runTimeout(timeout) {
            database.document(user.id).update(
                mapOf(
                    "devices" to user.devices,
                    "user" to user.user,
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
        }.onFailure {
            emit(TimeoutError)
        }
    }.flowOn(Dispatchers.IO)
    
    override suspend fun deleteUser(user: UserDTO) = flow {
        val coroutineContext = currentCoroutineContext()
        runTimeout(timeout) {
            database.document(user.id).delete().addOnSuccessListener {
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
        }.onFailure {
            emit(TimeoutError)
        }
    }.flowOn(Dispatchers.IO)
}