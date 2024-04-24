package com.ephirium.coffee.data.auth.di

import com.ephirium.coffee.data.auth.config.RouteProvider
import com.ephirium.coffee.data.auth.repository.AuthRepository
import com.ephirium.coffee.data.auth.repository.RemoteAuthRepository
import com.ephirium.coffee.data.auth.service.AuthService
import com.ephirium.coffee.data.auth.service.KtorAuthService
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit.MILLISECONDS

val authDataModule = module {
    singleOf(::KtorAuthService) bind AuthService::class
    singleOf(::RemoteAuthRepository) bind AuthRepository::class
    single {
        HttpClient(CIO) {
            expectSuccess = true
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    ignoreUnknownKeys = true
                })
            }
            install(HttpTimeout) {
                connectTimeoutMillis = 5.seconds.toLong(MILLISECONDS)
                socketTimeoutMillis = 5.seconds.toLong(MILLISECONDS)
                requestTimeoutMillis = 5.seconds.toLong(MILLISECONDS)
            }
        }
    } bind HttpClient::class
    
    singleOf(::RouteProvider) bind RouteProvider::class
}