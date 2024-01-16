package com.ephirium.coffee.app.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.ephirium.coffee.app.notification.DailyCoffeeHelper
import org.koin.java.KoinJavaComponent.inject

class DailyCoffeeReceiver : BroadcastReceiver() {
    private val dailyCoffeeHelper: DailyCoffeeHelper by inject(DailyCoffeeHelper::class.java)

    override fun onReceive(context: Context, intent: Intent) {
        dailyCoffeeHelper.createNotification()
    }
}