package com.ephirium.coffee.domain.usecase.user

import com.ephirium.coffee.domain.repository.UserRepository

class GetUserByIdUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(id: String) = userRepository.getUserById(id)
}