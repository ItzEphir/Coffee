package com.ephirium.coffee.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ephirium.coffee.app.ui.navigation.MainGraph.AUTH
import com.ephirium.coffee.core.navigation.ext.NavHost
import com.ephirium.coffee.core.navigation.ext.navComponent

@Composable
fun Navigation(navController: NavHostController = rememberNavController()) {
    
    NavHost(navController = navController, startComponent = AUTH.navComponent) {
        MainGraph.entries.forEach { screen ->
            navComponent(navController, screen.navComponent)
        }
    }
}