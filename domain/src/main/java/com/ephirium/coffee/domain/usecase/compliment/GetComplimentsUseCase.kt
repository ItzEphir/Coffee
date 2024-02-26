package com.ephirium.coffee.domain.usecase.compliment

import com.ephirium.coffee.domain.repository.ComplimentRepository

class GetComplimentsUseCase(private val complimentRepository: ComplimentRepository) {
    
    suspend operator fun invoke() = complimentRepository.getCompliments()
}