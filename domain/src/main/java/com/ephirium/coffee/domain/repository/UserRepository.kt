package com.ephirium.coffee.domain.repository

import com.ephirium.coffee.common.Status
import com.ephirium.coffee.domain.model.present.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    
    suspend fun getUsers(): Flow<Status<List<User>>>
    
    suspend fun getUserById(id: String) : Flow<Status<User>>
    
    suspend fun updateUser(user: User): Flow<Status<Unit>>
}