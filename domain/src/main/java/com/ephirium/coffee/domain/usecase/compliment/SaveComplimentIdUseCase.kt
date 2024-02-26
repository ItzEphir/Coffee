package com.ephirium.coffee.domain.usecase.compliment

import com.ephirium.coffee.domain.repository.ComplimentIdRepository

class SaveComplimentIdUseCase(private val complimentIdRepository: ComplimentIdRepository) {
    
    suspend operator fun invoke(id: String) = complimentIdRepository.saveComplimentId(id)
}