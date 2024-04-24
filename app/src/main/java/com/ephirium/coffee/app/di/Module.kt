package com.ephirium.coffee.app.di

import android.app.AlarmManager
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import com.ephirium.coffee.app.BuildConfig
import com.ephirium.coffee.app.notification.DailyCoffeeHelper
import com.ephirium.coffee.app.preferences.PreferenceManager
import com.ephirium.coffee.app.presentation.viewmodel.AuthScreenViewModel
import com.ephirium.coffee.app.presentation.viewmodel.ComplimentScreenViewModel
import com.ephirium.coffee.data.auth.config.RemoteConfig
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

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
    
    singleOf(::PreferenceManager)
    
    singleOf(::DailyCoffeeHelper)
    
    single { androidContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager }
}