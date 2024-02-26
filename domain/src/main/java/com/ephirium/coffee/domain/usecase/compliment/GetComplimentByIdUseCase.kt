package com.ephirium.coffee.domain.usecase.compliment

import com.ephirium.coffee.domain.repository.ComplimentRepository

class GetComplimentByIdUseCase(private val complimentRepository: ComplimentRepository) {
    
    suspend operator fun invoke(id: String) = complimentRepository.getComplimentById(id)
}