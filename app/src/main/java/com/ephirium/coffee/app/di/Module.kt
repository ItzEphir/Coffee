package com.ephirium.coffee.app.di

import android.app.AlarmManager
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import com.ephirium.coffee.app.BuildConfig
import com.ephirium.coffee.app.presentation.viewmodel.AuthScreenViewModel
import com.ephirium.coffee.app.presentation.viewmodel.ComplimentScreenViewModel
import com.ephirium.coffee.core.network.RemoteConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit.MILLISECONDS

val Context.dataStore by preferencesDataStore("app_preferences")

val appModule = module {
    
    single {
        androidContext().dataStore
    } bind DataStore::class
    
    single {
        RemoteConfig(url = "http://${BuildConfig.SERVER_URL}", port = BuildConfig.SERVER_PORT)
    }
    
    viewModelOf(::ComplimentScreenViewModel)
    
    viewModelOf(::AuthScreenViewModel)
    
    single { androidContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager }
    
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
}