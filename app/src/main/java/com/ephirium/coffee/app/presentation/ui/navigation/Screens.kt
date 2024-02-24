package com.ephirium.coffee.app.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.ephirium.coffee.app.presentation.ui.components.screens.auth.AuthScreen
import com.ephirium.coffee.app.presentation.ui.components.screens.compliment.ComplimentScreen

enum class Screens(val route: String, val screen: @Composable (NavController) -> Unit) {
    COMPLIMENT(route = "Compliment", screen = { navController ->
        ComplimentScreen(navController)
    }),
    
    AUTH(route = "Auth", screen = { navController ->
        AuthScreen(navController)
    }),
}