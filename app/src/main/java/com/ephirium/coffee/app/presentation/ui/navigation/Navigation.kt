package com.ephirium.coffee.app.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = mainGraph) {
        mainGraph(navController, mainGraph)
    }
}

const val mainGraph = "main_graph"