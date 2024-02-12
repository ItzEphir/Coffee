package com.ephirium.coffee.domain.usecase.compliment

import com.ephirium.coffee.domain.mapper.convertForPresentation
import com.ephirium.coffee.domain.repository.ComplimentRepositoryBase
import kotlinx.coroutines.flow.mapNotNull

class GetRandomComplimentUseCase(private val complimentsRepositoryBase: ComplimentRepositoryBase) {
    
    suspend fun execute(exceptedId: String? = null) =
        complimentsRepositoryBase.getCompliments().mapNotNull { complimentsResult ->
            complimentsResult.getOrThrow().filterNot { it.id == exceptedId }.random()
                .convertForPresentation()
        }
}