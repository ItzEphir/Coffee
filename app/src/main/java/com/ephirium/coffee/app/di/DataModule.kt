package com.ephirium.coffee.app.di

import com.ephirium.coffee.data.repository.ComplimentRepository
import com.ephirium.coffee.domain.repository.ComplimentRepositoryBase
import org.koin.dsl.module

val dataModule = module {
    single<ComplimentRepositoryBase> { ComplimentRepository() }
}