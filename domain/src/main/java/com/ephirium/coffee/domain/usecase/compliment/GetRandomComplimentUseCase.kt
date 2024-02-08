package com.ephirium.coffee.domain.usecase.compliment

import com.ephirium.coffee.domain.model.present.Compliment
import kotlinx.coroutines.flow.*

class GetRandomComplimentUseCase(private val getComplimentsUseCase: GetComplimentsUseCase) {
    
    suspend fun execute(exceptedId: String? = null): Flow<Compliment> =
        getComplimentsUseCase.execute().mapNotNull { compliments ->
            compliments.filter { it.id != exceptedId }.random()
        }
}