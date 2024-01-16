package com.ephirium.coffee.domain.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import com.ephirium.coffee.domain.usecase.GetComplimentsUseCase
import com.ephirium.coffee.domain.usecase.GetComplimentByIdUseCase

val domainModule = module{
    factoryOf(::GetComplimentsUseCase)

    factoryOf(::GetComplimentByIdUseCase)
}