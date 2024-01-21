package com.ephirium.coffee.domain.di

import com.ephirium.coffee.domain.usecase.GetComplimentByIdUseCase
import com.ephirium.coffee.domain.usecase.GetComplimentsUseCase
import com.ephirium.coffee.domain.usecase.GetRandomComplimentUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetComplimentsUseCase)
    
    factoryOf(::GetComplimentByIdUseCase)
    
    factoryOf(::GetRandomComplimentUseCase)
}