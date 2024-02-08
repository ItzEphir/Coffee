package com.ephirium.coffee.domain.di

import com.ephirium.coffee.domain.usecase.compliment.GetComplimentByIdUseCase
import com.ephirium.coffee.domain.usecase.compliment.GetComplimentsUseCase
import com.ephirium.coffee.domain.usecase.compliment.GetRandomComplimentUseCase
import com.ephirium.coffee.domain.usecase.user.GetUserByIdUseCase
import com.ephirium.coffee.domain.usecase.user.PostUserUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetComplimentsUseCase)
    
    factoryOf(::GetComplimentByIdUseCase)
    
    factoryOf(::GetRandomComplimentUseCase)
    
    factoryOf(::GetUserByIdUseCase)
    
    factoryOf(::PostUserUseCase)
}