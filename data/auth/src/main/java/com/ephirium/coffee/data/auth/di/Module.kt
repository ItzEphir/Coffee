package com.ephirium.coffee.data.auth.di

import com.ephirium.coffee.data.auth.config.RouteProvider
import com.ephirium.coffee.data.auth.repository.AuthRepository
import com.ephirium.coffee.data.auth.repository.RemoteAuthRepository
import com.ephirium.coffee.data.auth.service.AuthService
import com.ephirium.coffee.data.auth.service.KtorAuthService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val authDataModule = module {
    singleOf(::KtorAuthService) bind AuthService::class
    singleOf(::RemoteAuthRepository) bind AuthRepository::class
    singleOf(::RouteProvider) bind RouteProvider::class
}