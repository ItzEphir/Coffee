package com.ephirium.coffee.app.presentation.ui.navigation

import com.ephirium.coffee.core.navigation.NavInnerGraph
import com.ephirium.coffee.core.navigation.components.NavComponent
import com.ephirium.coffee.core.navigation.components.NavDestination
import com.ephirium.coffee.core.navigation.popBackStack
import com.ephirium.coffee.feature.auth.ui.screen.AuthScreen

enum class Screens(val navComponent: NavComponent) {
    MAIN(
        navComponent = NavInnerGraph(
            route = "main",
            startComponent = NavDestination(
                route = "compliment",
                destination = { navController ->
                
                },
            ),
            additionalComponents = listOf(),
        )
    ),
    AUTH(
        navComponent = NavDestination(
            route = "auth",
            destination = { navController ->
                AuthScreen(onAuthorized = {
                    navController.popBackStack(navComponent = MAIN.navComponent, inclusive = true)
                })
            },
        )
    )
}