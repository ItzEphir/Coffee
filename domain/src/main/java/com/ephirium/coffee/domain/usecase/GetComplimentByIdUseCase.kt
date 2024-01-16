package com.ephirium.coffee.domain.usecase

import com.ephirium.coffee.domain.mapper.convert
import com.ephirium.coffee.domain.model.present.Compliment
import com.ephirium.coffee.domain.repository.ComplimentRepositoryBase
import kotlinx.coroutines.flow.firstOrNull

class GetComplimentByIdUseCase(private val complimentRepository: ComplimentRepositoryBase) {
    suspend fun execute(
        id: String,
        onReceive: (compliment: Compliment) -> Unit,
        onException: (exception: Exception) -> Unit,
    ) {
        val complimentFlow = complimentRepository.getComplimentFlowById(id)

        when (val compliment = complimentFlow.firstOrNull()) {
            null -> onException(NullPointerException("Compliment wasn`t loaded"))

            else -> compliment.onSuccess {
                onReceive(it.convert())
            }.onFailure {
                onException(Exception(it))
            }
        }
    }

}