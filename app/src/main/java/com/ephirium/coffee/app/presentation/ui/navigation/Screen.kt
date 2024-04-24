package com.ephirium.coffee.app.presentation.ui.navigation

import com.ephirium.coffee.core.navigation.ext.NavInnerGraph
import com.ephirium.coffee.core.navigation.components.NavComponent
import com.ephirium.coffee.core.navigation.components.NavDestination
import com.ephirium.coffee.core.navigation.ext.navigate
import com.ephirium.coffee.core.navigation.ext.popUpTo
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
                    navController.navigate(MAIN.navComponent){
                        popUpTo(AUTH.navComponent){
                            inclusive = true
                            saveState = true
                        }
                    }
                })
            },
        )
    )
}