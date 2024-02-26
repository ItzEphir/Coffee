package com.ephirium.coffee.domain.usecase.compliment

import com.ephirium.coffee.common.map
import com.ephirium.coffee.domain.repository.ComplimentRepository
import kotlinx.coroutines.flow.map

class GetRandomComplimentUseCase(private val complimentsRepository: ComplimentRepository) {
    
    suspend operator fun invoke(exceptedId: String? = null) =
        complimentsRepository.getCompliments().map { status ->
            status.map { compliments ->
                compliments.filterNot { compliment -> compliment.id == exceptedId }.random()
            }
        }
}