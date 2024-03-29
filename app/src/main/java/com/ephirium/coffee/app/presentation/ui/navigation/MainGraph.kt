package com.ephirium.coffee.app.presentation.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.mainGraph(navController: NavController, route: String) {
    navigation(startDestination = Screens.AUTH.route, route = route) {
        for (screen in Screens.entries) {
            composable(screen.route) {
                screen.screen(navController)
            }
        }
    }
}