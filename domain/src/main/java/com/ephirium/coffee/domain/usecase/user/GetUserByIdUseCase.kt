package com.ephirium.coffee.domain.usecase.user

import com.ephirium.coffee.domain.mapper.convertForPresentation
import com.ephirium.coffee.domain.model.present.User
import com.ephirium.coffee.domain.repository.UserRepositoryBase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull

class GetUserByIdUseCase(private val userRepository: UserRepositoryBase) {
    suspend fun execute(id: String) =
        userRepository.getUserById(id).mapNotNull { userResult ->
            userResult.getOrThrow().convertForPresentation()
        }
}