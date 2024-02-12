package com.ephirium.coffee.domain.usecase.compliment

import com.ephirium.coffee.domain.mapper.convertForPresentation
import com.ephirium.coffee.domain.repository.ComplimentRepositoryBase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.mapNotNull

class GetSavedComplimentUseCase(private val complimentRepository: ComplimentRepositoryBase) {
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun execute() =
        complimentRepository.getSavedComplimentIdLocally().mapNotNull { it.getOrThrow() }
            .flatMapLatest { id ->
                complimentRepository.getComplimentById(id)
                    .mapNotNull { it.getOrThrow().convertForPresentation() }
            }
}