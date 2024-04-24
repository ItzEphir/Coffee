package com.ephirium.coffee.data.auth_token.di

import com.ephirium.coffee.data.auth_token.repository.TokenDataStoreRepository
import com.ephirium.coffee.data.auth_token.repository.TokenRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val authTokenDataModule = module {
    singleOf(::TokenDataStoreRepository) bind TokenRepository::class
}