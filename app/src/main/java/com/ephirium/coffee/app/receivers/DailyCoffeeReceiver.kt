package com.ephirium.coffee.app.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.ephirium.coffee.app.notification.DailyCoffeeHelper

class DailyCoffeeReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val dailyCoffeeHelper = DailyCoffeeHelper(context)
        dailyCoffeeHelper.createNotification()
    }
}