package com.ephirium.coffee.domain.repository

import com.ephirium.coffee.domain.model.dto.UserDTOBase
import kotlinx.coroutines.flow.Flow

interface AuthRepositoryBase {
    
    suspend fun createNewUser(login: String, email: String, password: String) : Flow<Result<UserDTOBase>>
    
}