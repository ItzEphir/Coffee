package com.ephirium.coffee.domain.usecase.user

import com.ephirium.coffee.common.flatMap
import com.ephirium.coffee.common.map
import com.ephirium.coffee.domain.repository.AuthRepository
import com.ephirium.coffee.domain.repository.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

class SignInByLoginUseCase(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
) {
    
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend operator fun invoke(login: String, password: String) = userRepository.getUsers().map { status ->
        status.map { users ->
            users.first { user -> user.login == login }
        }
    }.flatMapLatest {
        it.flatMap {
            authRepository.signIn(it.email, password)
        }
    }
    
}