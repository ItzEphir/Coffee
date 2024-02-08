package com.ephirium.coffee.app.notification

import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import androidx.core.app.NotificationCompat
import com.ephirium.coffee.app.R
import com.ephirium.coffee.app.R.array
import com.ephirium.coffee.app.preferences.PreferenceManager
import com.ephirium.coffee.app.presentation.ui.MainActivity
import com.ephirium.coffee.app.receivers.DailyCoffeeReceiver
import java.util.Date
import kotlin.random.Random
import kotlin.random.nextInt


class DailyCoffeeHelper(
    private val context: Context,
    private val alarmManager: AlarmManager,
    private val preferenceManager: PreferenceManager
) {
    
    fun createNotification() {
        val compliments = context.resources.getStringArray(array.compliments).toList()
        
        preferenceManager.compliment?.let { complimentNonNull ->
            
            val intent = Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            val pendingIntent = PendingIntent.getActivity(
                context, System.currentTimeMillis().toInt(), intent, PendingIntent.FLAG_IMMUTABLE
            )
            
            val notification =
                NotificationCompat.Builder(context, CHANNEL_ID).setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.ic_coffee_logo)
                    .setContentTitle(context.getString(R.string.app_name))
                    .setContentText(complimentNonNull).build()
            
            preferenceManager.compliment = compliments[getIndex(compliments, complimentNonNull)]
            
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(1, notification)
        }
    }
    
    fun setAlarm(calendar: Calendar) {
        if (calendar.time < Date()) {
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            Intent(context, DailyCoffeeReceiver::class.java),
            PendingIntent.FLAG_IMMUTABLE
        )
        
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent
        )
    }
    
    private fun getIndex(compliments: List<String>, compliment: String): Int =
        when (val index = Random.nextInt(compliments.indices)) {
            compliments.indexOf(compliment) -> getIndex(compliments, compliment)
            else                            -> index
        }
    
    companion object {
        const val CHANNEL_ID = "daily_coffee_channel"
    }
}
