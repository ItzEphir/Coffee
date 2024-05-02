package com.ephirium.coffee.app.ui.navigation

import com.ephirium.coffee.app.ui.navScreens.mainGraph.AuthNavScreen
import com.ephirium.coffee.app.ui.navScreens.mainGraph.MainNavScreen
import com.ephirium.coffee.core.navigation.components.NavComponent
import com.ephirium.coffee.core.navigation.components.NavDestination

enum class MainGraph(val navComponent: NavComponent) {
    MAIN(
        navComponent = NavDestination(
            route = "main",
            destination = { navController ->
                MainNavScreen(navController = navController)
            }
        )
    ),
    AUTH(
        navComponent = NavDestination(
            route = "auth",
            destination = { navController ->
                AuthNavScreen(navController = navController)
            },
        )
    )
}