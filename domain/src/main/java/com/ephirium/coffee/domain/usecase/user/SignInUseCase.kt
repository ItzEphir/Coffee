package com.ephirium.coffee.domain.usecase.user

import com.ephirium.coffee.domain.repository.AuthRepositoryBase

class SignInUseCase(private val authRepository: AuthRepositoryBase) {
    
    fun execute(login: String, password: String){
    
    }
    
}