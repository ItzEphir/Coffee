package com.ephirium.coffee.app.notification

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.ephirium.coffee.app.R
import com.ephirium.coffee.app.R.array
import kotlin.random.Random
import kotlin.random.nextInt


class DailyCoffeeHelper(private val context: Context) {

    private val sharedPreferences = context.getSharedPreferences("pref", Context.MODE_PRIVATE)

    fun createNotification() {
        val compliments = context.resources.getStringArray(array.compliments).toList()

        notificationMessage?.let { complimentNonNull ->
            val notification = NotificationCompat.Builder(context, CHANNEL_ID).setSmallIcon(
                R.drawable.ic_launcher_foreground
            ).setContentTitle(context.getString(R.string.app_name))
                .setContentText(complimentNonNull).build()

            sharedPreferences.edit().putString(
                "compliment", compliments[getIndex(compliments, complimentNonNull)]
            ).apply()

            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(1, notification)
        }
    }

    private fun getIndex(compliments: List<String>, compliment: String): Int =
        when (val index = Random.nextInt(compliments.indices)) {
            compliments.indexOf(compliment) -> getIndex(compliments, compliment)
            else                            -> index
        }

    private val notificationMessage: String? = run {
        sharedPreferences.getString("compliment", null)
    }

    companion object {
        const val CHANNEL_ID = "daily_coffee_channel"
    }
}
