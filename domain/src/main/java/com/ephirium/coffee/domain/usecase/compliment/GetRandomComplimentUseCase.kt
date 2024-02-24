package com.ephirium.coffee.domain.usecase.compliment

import com.ephirium.coffee.common.Status.*
import com.ephirium.coffee.domain.repository.ComplimentRepository
import kotlinx.coroutines.flow.map

class GetRandomComplimentUseCase(private val complimentsRepository: ComplimentRepository) {
    
    suspend fun execute(exceptedId: String? = null) =
        complimentsRepository.getCompliments().map { status ->
            when (status) {
                is Success      -> Success(status.result.filterNot { it.id == exceptedId }.random())
                is TimeoutError -> TimeoutError
                is NetworkError -> NetworkError
                is Error        -> Error
            }
        }
}