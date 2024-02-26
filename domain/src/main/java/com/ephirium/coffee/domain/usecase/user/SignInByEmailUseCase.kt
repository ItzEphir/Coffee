package com.ephirium.coffee.domain.usecase.user

import com.ephirium.coffee.domain.repository.AuthRepository

class SignInByEmailUseCase(private val authRepository: AuthRepository) {
    
    suspend operator fun invoke(email: String, password: String) =
        authRepository.signIn(email, password)
}