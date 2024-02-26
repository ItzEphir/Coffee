package com.ephirium.coffee.domain.usecase.user

import com.ephirium.coffee.domain.repository.AuthRepository

class SignUpUseCase(private val authRepository: AuthRepository) {
    
    suspend operator fun invoke(login: String, email: String, password: String) =
        authRepository.singUp(login, email, password)
}