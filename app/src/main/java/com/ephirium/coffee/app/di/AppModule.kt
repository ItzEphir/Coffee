package com.ephirium.coffee.app.di

import android.app.AlarmManager
import android.content.Context
import com.ephirium.coffee.app.notification.DailyCoffeeHelper
import com.ephirium.coffee.app.preferences.PreferenceManager
import com.ephirium.coffee.app.presentation.viewmodel.ComplimentScreenViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    
    viewModelOf(::ComplimentScreenViewModel)
    
    singleOf(::PreferenceManager)
    
    singleOf(::DailyCoffeeHelper)
    
    single { androidContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager }
}