package com.ephirium.coffee.domain.di

import com.ephirium.coffee.domain.usecase.compliment.*
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val complimentsModule = module {
    factoryOf(::GetComplimentsUseCase)
    
    factoryOf(::SaveComplimentIdUseCase)
    
    factoryOf(::GetComplimentByIdUseCase)
    
    factoryOf(::GetSavedComplimentUseCase)
    
    factoryOf(::GetRandomComplimentUseCase)
}