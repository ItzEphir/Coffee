package com.ephirium.coffee.domain.repository

import com.ephirium.coffee.domain.model.present.User
import kotlinx.coroutines.flow.Flow

interface AuthRepositoryBase {
    
    suspend fun createNewUser(login: String, email: String, password: String): Flow<Result<User>>
    
    suspend fun signIn(login: String, password: String): Flow<Result<User>>
    
}