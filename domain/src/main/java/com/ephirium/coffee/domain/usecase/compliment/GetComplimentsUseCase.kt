package com.ephirium.coffee.domain.usecase.compliment

import com.ephirium.coffee.domain.mapper.convertForPresentation
import com.ephirium.coffee.domain.model.present.Compliment
import com.ephirium.coffee.domain.repository.ComplimentRepositoryBase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetComplimentsUseCase(private val complimentRepository: ComplimentRepositoryBase) {
    
    suspend fun execute(): Flow<List<Compliment>> =
        complimentRepository.getCompliments().map { listResult ->
            listResult.getOrThrow().map {
                it.convertForPresentation()
            }
        }
}