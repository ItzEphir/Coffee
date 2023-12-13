package com.ephirium.coffee.app.ui.activity

import android.Manifest.permission
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import com.ephirium.coffee.app.R
import com.ephirium.coffee.app.presentation.viewmodel.ComplimentViewModel
import com.ephirium.coffee.app.receivers.DailyCoffeeReceiver
import com.ephirium.coffee.app.ui.components.MainScreen
import com.ephirium.coffee.app.ui.theme.CoffeeTheme
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.util.Date


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val complimentViewModel: ComplimentViewModel = getViewModel()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    permission.POST_NOTIFICATIONS
                ), 0
            )
        }

        setContent {

            LaunchedEffect(key1 = Unit) {
                complimentViewModel.loadCompliments(
                    resources.getStringArray(R.array.compliments).toList()
                )
            }

            CoffeeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }

    fun setAlarm() {
        val calendar: Calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 10)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        if (calendar.time < Date()) {
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }
        val intent = Intent(applicationContext, DailyCoffeeReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext, 0, intent, PendingIntent.FLAG_IMMUTABLE
        )
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent
        )
    }
}