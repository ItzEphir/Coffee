package com.ephirium.coffee.domain.usecase.compliment

import com.ephirium.coffee.common.Status
import com.ephirium.coffee.domain.repository.ComplimentIdRepository
import com.ephirium.coffee.domain.repository.ComplimentRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last

class GetSavedComplimentUseCase(
    private val complimentRepository: ComplimentRepository,
    private val complimentIdRepository: ComplimentIdRepository,
) {
    
    suspend fun execute() = when (val id = complimentIdRepository.getSavedComplimentId().last()) {
        null -> flow { emit(Status.Error) }
        else -> complimentRepository.getComplimentById(id)
    }
}