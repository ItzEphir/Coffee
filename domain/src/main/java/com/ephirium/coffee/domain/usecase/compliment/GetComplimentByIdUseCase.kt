package com.ephirium.coffee.domain.usecase.compliment

import com.ephirium.coffee.domain.repository.ComplimentRepository

class GetComplimentByIdUseCase(private val complimentRepository: ComplimentRepository) {
    
    suspend fun execute(id: String) = complimentRepository.getComplimentById(id)
}