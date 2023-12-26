package com.ephirium.coffee.domain.usecase

import com.ephirium.coffee.domain.model.ComplimentDtoBase
import com.ephirium.coffee.domain.repository.ComplimentRepositoryBase

class GetComplimentsUseCase(private val complimentRepository: ComplimentRepositoryBase) {
    suspend fun execute(
        onReceive: (List<ComplimentDtoBase>) -> Unit,
        onException: (exception: Exception) -> Unit,
    ) {
        val complimentFlow = complimentRepository.getCompliments {
            onException(it)
        }

        val data = mutableListOf<ComplimentDtoBase>()
        try {
            complimentFlow.collect {
                data.add(it)
                onReceive(data.toList())
            }
        } catch (e: Exception) {
            onException(e)
        }
    }
}