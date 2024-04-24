package com.ephirium.coffee.app.presentation.ui

import android.Manifest.permission
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import com.ephirium.coffee.app.presentation.ui.navigation.Navigation
import com.ephirium.coffee.app.presentation.ui.theme.CoffeeTheme
import org.koin.compose.KoinContext


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        
        if (VERSION.SDK_INT >= VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    permission.POST_NOTIFICATIONS
                ), 0
            )
        }
        
        setContent {
            KoinContext {
                CoffeeTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background,
                    ) {
                        Navigation()
                    }
                }
            }
        }
    }
}