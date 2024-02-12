package com.ephirium.coffee.domain.usecase.user

import com.ephirium.coffee.domain.mapper.convertForPresentation
import com.ephirium.coffee.domain.model.present.User
import com.ephirium.coffee.domain.repository.UserRepositoryBase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull

class PostUserUseCase(private val userRepository: UserRepositoryBase) {
    suspend fun execute(user: User) =
        userRepository.postUser(user).mapNotNull { userResult ->
            userResult.getOrThrow().convertForPresentation()
        }
}