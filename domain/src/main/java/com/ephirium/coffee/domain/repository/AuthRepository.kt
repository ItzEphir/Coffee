package com.ephirium.coffee.domain.repository

import com.ephirium.coffee.common.Status
import com.ephirium.coffee.domain.model.present.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    
    suspend fun singUp(login: String, email: String, password: String): Flow<Status<User>>
    
    suspend fun signIn(email: String, password: String): Flow<Status<User>>
    
}