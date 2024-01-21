package com.ephirium.coffee.domain.usecase

import com.ephirium.coffee.domain.model.present.Compliment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull

class GetRandomComplimentUseCase(private val getComplimentsUseCase: GetComplimentsUseCase) {
    
    suspend fun execute(exceptedId: String? = null): Flow<Compliment> =
        getComplimentsUseCase.execute().mapNotNull { compliments ->
            compliments.filter { it.id != exceptedId }.random()
        }
}