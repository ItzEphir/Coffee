package com.ephirium.coffee.domain.usecase.user

import com.ephirium.coffee.domain.model.present.User
import com.ephirium.coffee.domain.repository.UserRepository

class UpdateUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(user: User) = userRepository.updateUser(user)
}