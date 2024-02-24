package com.ephirium.coffee.domain.usecase.user

import com.ephirium.coffee.domain.repository.AuthRepositoryBase
import kotlinx.coroutines.flow.mapNotNull

class CreateNewUserUseCase(private val authRepository: AuthRepositoryBase) {
    
    suspend fun execute(login: String, email: String, password: String) =
        authRepository.createNewUser(login, email, password)
            .mapNotNull { result -> result.map { it } }
}