package com.ephirium.coffee.app.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ephirium.coffee.core.navigation.ext.NavHost
import com.ephirium.coffee.core.navigation.ext.navComponent

@Composable
fun Navigation(navController: NavHostController = rememberNavController()) {
    
    NavHost(navController = navController, startComponent = Screen.AUTH.navComponent) {
        Screen.entries.forEach { screen ->
            navComponent(navController, screen.navComponent)
        }
    }
}