package com.ephirium.coffee.domain.usecase.compliment

import com.ephirium.coffee.domain.repository.ComplimentRepositoryBase
import kotlinx.coroutines.flow.mapNotNull

class SaveComplimentIdUseCase(private val complimentRepository: ComplimentRepositoryBase) {
    
    suspend fun execute(id: String) =
        complimentRepository.saveComplimentIdLocally(id).mapNotNull { it.getOrThrow() }
}