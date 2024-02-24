package com.ephirium.coffee.databases.users.datastore

import com.ephirium.coffee.databases.users.models.UserDTO
import com.ephirium.coffee.common.Status
import kotlinx.coroutines.flow.Flow

interface UserDataStore {

    suspend fun getUsers() : Flow<Status<List<UserDTO>>>
    
    suspend fun getUserById(id: String) : Flow<Status<UserDTO>>
    
    suspend fun createUser(user: UserDTO): Flow<Status<Unit>>
    
    suspend fun updateUser(user: UserDTO): Flow<Status<Unit>>
    
    suspend fun deleteUser(user: UserDTO): Flow<Status<Unit>>

}