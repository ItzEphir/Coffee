package com.ephirium.coffee.auth.datastore

import com.ephirium.coffee.common.Status
import com.ephirium.coffee.common.Status.*
import com.ephirium.coffee.common.onTimeout
import com.ephirium.coffee.common.runTimeout
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

internal class AuthDataStoreImpl : AuthDataStore {
    
    override suspend fun signIn(email: String, password: String): Flow<Status<FirebaseUser>> =
        flow {
            runTimeout(10.seconds) {
                Firebase.auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                    launch {
                        emit(
                            when (val user = it.user) {
                                null -> Error
                                else -> Success(user)
                            }
                        )
                    }
                }.addOnFailureListener {
                    launch {
                        emit(
                            when (it) {
                                is FirebaseAuthException -> NetworkError
                                else                     -> Error
                            }
                        )
                    }
                }
            }.onTimeout { emit(TimeoutError) }
        }.flowOn(Dispatchers.IO).conflate()
    
    
    override suspend fun signUp(email: String, password: String): Flow<Status<FirebaseUser>> =
        flow {
            runTimeout(10.seconds) {
                Firebase.auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                    launch {
                        emit(
                            when (val user = it.user) {
                                null -> Error
                                else -> Success(user)
                            }
                        )
                    }
                    
                }.addOnFailureListener {
                    launch {
                        emit(
                            when (it) {
                                is FirebaseAuthException -> NetworkError
                                else                     -> Error
                            }
                        )
                    }
                }
            }.onTimeout { emit(TimeoutError) }
        }.flowOn(Dispatchers.IO).conflate()
    
    override suspend fun delete(user: FirebaseUser): Flow<Status<Unit>> = flow {
        Firebase.auth.signOut()
        runTimeout(10.seconds) {
            user.delete().addOnSuccessListener {
                launch {
                    emit(Success(Unit))
                }
            }.addOnFailureListener {
                launch {
                    emit(
                        when (it) {
                            is FirebaseAuthException -> NetworkError
                            else                     -> Error
                        }
                    )
                }
            }
        }.onTimeout {
            emit(TimeoutError)
        }
    }
}