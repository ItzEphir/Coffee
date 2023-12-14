package com.ephirium.coffee.app.di

import com.ephirium.coffee.domain.usecase.GetComplimentsUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetComplimentsUseCase(get()) }
}