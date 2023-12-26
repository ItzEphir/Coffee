package com.ephirium.coffee.data.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.ephirium.coffee.data.repository.ComplimentRepositoryImpl

val dataModule = module{
    singleOf(::ComplimentRepositoryImpl)
}