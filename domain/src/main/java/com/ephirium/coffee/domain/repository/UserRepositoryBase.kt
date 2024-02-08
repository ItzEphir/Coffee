package com.ephirium.coffee.domain.repository

import com.ephirium.coffee.domain.model.dto.UserDTOBase
import com.ephirium.coffee.domain.model.present.User
import kotlinx.coroutines.flow.Flow

interface UserRepositoryBase {
    suspend fun getUserById(id: String) : Flow<Result<UserDTOBase>>
    
    suspend fun postUser(user: User) : Flow<Result<UserDTOBase>>
}