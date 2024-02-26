package com.ephirium.coffee.domain.di

import com.ephirium.coffee.domain.usecase.user.*
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val usersModule = module{
    factoryOf(::SignUpUseCase)
    
    factoryOf(::UpdateUserUseCase)
    
    factoryOf(::GetUserByIdUseCase)
    
    factoryOf(::SignInByEmailUseCase)
    
    factoryOf(::SignInByLoginUseCase)
}