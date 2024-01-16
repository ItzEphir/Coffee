package com.ephirium.coffee.domain.usecase

import com.ephirium.coffee.domain.mapper.convert
import com.ephirium.coffee.domain.model.present.Compliment
import com.ephirium.coffee.domain.repository.ComplimentRepositoryBase

class GetComplimentsUseCase(private val complimentRepository: ComplimentRepositoryBase) {
    suspend fun execute(
        onReceive: (compliments: List<Compliment>) -> Unit,
        onException: (exception: Exception) -> Unit,
    ) {
        val complimentFlow = complimentRepository.getComplimentsFlow()
        val data = mutableListOf<Compliment>()

        complimentFlow.collect { result ->
            result.onSuccess { compliment ->
                data.add(compliment.convert())
                onReceive(data.toList())
            }.onFailure {
                onException(Exception(it))
            }
        }
    }
}