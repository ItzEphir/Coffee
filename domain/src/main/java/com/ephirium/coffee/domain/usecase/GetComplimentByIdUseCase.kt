package com.ephirium.coffee.domain.usecase

import com.ephirium.coffee.domain.mapper.convertForPresentation
import com.ephirium.coffee.domain.model.present.Compliment
import com.ephirium.coffee.domain.repository.ComplimentRepositoryBase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull

class GetComplimentByIdUseCase(private val complimentRepository: ComplimentRepositoryBase) {
    
    suspend fun execute(id: String): Flow<Compliment> =
        complimentRepository.getComplimentFlowById(id).mapNotNull { complimentResult ->
            complimentResult.getOrThrow().convertForPresentation()
        }
}