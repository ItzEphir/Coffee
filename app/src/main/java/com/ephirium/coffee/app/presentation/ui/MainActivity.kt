package com.ephirium.coffee.app.presentation.ui

import android.Manifest.permission
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
import com.ephirium.coffee.app.presentation.ui.components.screens.compliment.ComplimentScreen
import com.ephirium.coffee.app.presentation.ui.navigation.Navigation
import com.ephirium.coffee.app.presentation.ui.theme.CoffeeTheme
import org.koin.androidx.viewmodel.ext.android.getViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    permission.POST_NOTIFICATIONS
                ), 0
            )
        }

        setContent {
            CoffeeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }
}