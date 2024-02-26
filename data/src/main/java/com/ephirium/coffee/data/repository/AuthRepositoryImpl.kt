package com.ephirium.coffee.data.repository

import com.ephirium.coffee.auth.datastore.AuthDataStore
import com.ephirium.coffee.common.flatMap
import com.ephirium.coffee.common.map
import com.ephirium.coffee.data.mappers.map
import com.ephirium.coffee.databases.users.datastore.UserDataStore
import com.ephirium.coffee.databases.users.models.UserDTO
import com.ephirium.coffee.domain.repository.AuthRepository
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

internal class AuthRepositoryImpl(
    private val authDataStore: AuthDataStore,
    private val userDataStore: UserDataStore,
    private val cloudMessaging: FirebaseMessaging,
) : AuthRepository {
    
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun singUp(
        login: String,
        email: String,
        password: String,
    ) = authDataStore.signUp(email, password).flatMapLatest { firebaseUserStatus ->
        val user = UserDTO(
            login = login,
            email = email,
        )
        cloudMessaging.token.addOnSuccessListener {
            user.devices = listOf(it)
        }
        firebaseUserStatus.flatMap {
            user.id = it.uid
            userDataStore.createUser(user).map { status -> status.map { user.map() } }
        }
    }
    
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun signIn(email: String, password: String) =
        authDataStore.signIn(email, password).flatMapLatest { firebaseUserStatus ->
            firebaseUserStatus.flatMap {
                userDataStore.getUserById(it.uid)
                    .map { status -> status.map { user -> user.map() } }
            }
        }
}