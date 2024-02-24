package com.ephirium.coffee.domain.usecase.compliment

import com.ephirium.coffee.domain.repository.ComplimentRepository

class GetComplimentsUseCase(private val complimentRepository: ComplimentRepository) {
    
    suspend fun execute() = complimentRepository.getCompliments()
}