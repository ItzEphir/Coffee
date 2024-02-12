package com.ephirium.coffee.data.repository

import com.ephirium.coffee.data.model.UserDTOImpl
import com.ephirium.coffee.data.storage.Database
import com.ephirium.coffee.domain.model.dto.UserDTOBase
import com.ephirium.coffee.domain.model.present.User
import com.ephirium.coffee.domain.repository.UserRepositoryBase
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UserRepositoryImpl : UserRepositoryBase {
    
    override suspend fun getUserById(id: String): Flow<Result<UserDTOBase>> =
        callbackFlow<Result<UserDTOBase>> {
            val registration =
                Database.users.document(id).addSnapshotListener { snapshot, exception ->
                    launch {
                        exception?.let {
                            close(it)
                            return@launch
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
                        
                        val dto = snapshot.toObject<UserDTOImpl>()
                        if (dto == null) {
                            close(NullPointerException("Document can`t be parsed to dto"))
                            return@launch
                        }
                        send(Result.success(dto))
                    }
                }
            
            awaitClose(registration::remove)
            
            invokeOnClose { throwable ->
                launch {
                    throwable?.let {
                        send(Result.failure(it))
                    }
                }
            }
        }.flowOn(Dispatchers.IO)
    
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun postUser(user: User): Flow<Result<UserDTOBase>> =
        callbackFlow<Result<Unit>> {
            launch {
                Database.users.document(user.id).set(
                    mapOf(
                        "user" to user.user,
                        "token" to user.token,
                    )
                ).addOnSuccessListener {
                    launch {
                        send(Result.success(Unit))
                    }
                }.addOnFailureListener {
                    close(it)
                }
            }
            
            awaitClose()
            
            invokeOnClose { throwable ->
                launch {
                    throwable?.let {
                        send(Result.failure(it))
                    }
                }
            }
        }.flatMapLatest { result ->
            result.getOrThrow()
            getUserById(user.id)
        }.catch { emit(Result.failure(it)) }.flowOn(Dispatchers.IO)
}