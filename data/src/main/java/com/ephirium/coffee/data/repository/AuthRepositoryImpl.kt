package com.ephirium.coffee.data.repository

import com.ephirium.coffee.data.storage.Database
import com.ephirium.coffee.domain.model.dto.UserDTOBase
import com.ephirium.coffee.domain.model.present.User
import com.ephirium.coffee.domain.repository.AuthRepositoryBase
import com.ephirium.coffee.domain.repository.UserRepositoryBase
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.UUID

class AuthRepositoryImpl(private val userRepository: UserRepositoryBase) : AuthRepositoryBase {
    
    private suspend fun createUser(email: String, password: String) = flow<Result<FirebaseUser>> {
        val coroutineContext = currentCoroutineContext()
        Database.auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
            val user = it.user ?: throw FirebaseAuthException("", "Auth result is null")
            user.sendEmailVerification().addOnSuccessListener {
                CoroutineScope(coroutineContext).launch {
                    emit(Result.success(user))
                }
            }.addOnFailureListener { exception ->
                CoroutineScope(coroutineContext).launch {
                    emit(Result.failure(exception))
                }
            }
        }
    }.catch { throwable ->
        emit(Result.failure(throwable))
    }.flowOn(Dispatchers.IO)
    
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun createNewUser(
        login: String,
        email: String,
        password: String,
    ): Flow<Result<UserDTOBase>> = createUser(email, password).flatMapLatest { result ->
        result.onFailure { throwable ->
            return@flatMapLatest flow {
                emit(Result.failure(throwable))
            }
        }
        Database.fcm.token.exception?.let { exception ->
            return@flatMapLatest flow {
                emit(Result.failure(exception))
            }
        }
        userRepository.postUser(
            User(
                UUID.randomUUID().toString(), result.getOrThrow().uid, listOf(Database.fcm.token.result)
            )
        )
    }.flowOn(Dispatchers.IO)
}