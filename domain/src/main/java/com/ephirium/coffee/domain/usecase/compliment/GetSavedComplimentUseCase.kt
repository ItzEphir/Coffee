package com.ephirium.coffee.domain.usecase.compliment

import com.ephirium.coffee.common.Status
import com.ephirium.coffee.domain.repository.ComplimentIdRepository
import com.ephirium.coffee.domain.repository.ComplimentRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow

class GetSavedComplimentUseCase(
    private val complimentRepository: ComplimentRepository,
    private val complimentIdRepository: ComplimentIdRepository,
) {
    
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend operator fun invoke() =
        complimentIdRepository.getSavedComplimentId().flatMapLatest { id ->
            when (id) {
                null -> flow { emit(Status.Error) }
                else -> complimentRepository.getComplimentById(id)
            }
        }
}