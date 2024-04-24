package com.ephirium.coffee.app.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ephirium.coffee.core.navigation.ext.navComponent

@Composable
fun Navigation() {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = mainGraph) {
        Screens.entries.forEach {
            navComponent(navController, it.navComponent)
        }
    }
}

const val mainGraph = "main_graph"