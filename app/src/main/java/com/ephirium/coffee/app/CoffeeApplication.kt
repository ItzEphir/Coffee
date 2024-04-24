package com.ephirium.coffee.app

import android.app.Application
import com.ephirium.coffee.app.di.appModule
import com.ephirium.coffee.feature.auth.di.authFeatureModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CoffeeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
    }

    private fun startKoin() {
        startKoin {
            androidLogger()
            androidContext(this@CoffeeApplication)
            modules(listOf(appModule, authFeatureModule))
        }
    }
}