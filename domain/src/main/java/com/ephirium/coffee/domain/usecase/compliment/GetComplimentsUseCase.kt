package com.ephirium.coffee.domain.usecase.compliment

import com.ephirium.coffee.domain.mapper.convertForPresentation
import com.ephirium.coffee.domain.repository.ComplimentRepositoryBase
import kotlinx.coroutines.flow.map

class GetComplimentsUseCase(private val complimentRepository: ComplimentRepositoryBase) {
    
    suspend fun execute() = complimentRepository.getCompliments().map { complimentsResult ->
        complimentsResult.getOrThrow().map {
            it.convertForPresentation()
        }
    }
}