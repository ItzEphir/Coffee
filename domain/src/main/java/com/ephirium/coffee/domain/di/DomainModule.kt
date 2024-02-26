package com.ephirium.coffee.domain.di

import org.koin.dsl.module

val domainModule = module {
    includes(complimentsModule, usersModule)
}