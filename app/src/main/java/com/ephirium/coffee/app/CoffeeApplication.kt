package com.ephirium.coffee.app

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import com.ephirium.coffee.app.di.appModule
import com.ephirium.coffee.app.notification.DailyCoffeeHelper
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
        val channel = NotificationChannel(
            DailyCoffeeHelper.CHANNEL_ID,
            getString(R.string.notification_channel_name),
            NotificationManager.IMPORTANCE_DEFAULT
        )
        channel.description = getString(R.string.notification_coffee_description)
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    private fun startKoin() {
        startKoin {
            androidLogger()
            androidContext(this@CoffeeApplication)
            modules(listOf( appModule, authFeatureModule))
        }
    }
}