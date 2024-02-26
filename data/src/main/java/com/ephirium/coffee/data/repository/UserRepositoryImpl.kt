package com.ephirium.coffee.data.repository

import com.ephirium.coffee.common.Status
import com.ephirium.coffee.common.map
import com.ephirium.coffee.data.mappers.map
import com.ephirium.coffee.databases.users.datastore.UserDataStore
import com.ephirium.coffee.databases.users.models.UserDTO
import com.ephirium.coffee.domain.model.present.User
import com.ephirium.coffee.domain.repository.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest

internal class UserRepositoryImpl(private val userDataStore: UserDataStore) : UserRepository {
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getUsers() = userDataStore.getUsers()
        .mapLatest { status -> status.map { users -> users.map { user -> user.map() } } }
    
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getUserById(id: String) =
        userDataStore.getUserById(id).mapLatest { status -> status.map { user -> user.map() } }
    
    override suspend fun updateUser(user: User): Flow<Status<Unit>> = userDataStore.updateUser(
        UserDTO(user.id, user.login, user.email, user.devices)
    )
}