package com.ephirium.coffee.core.navigation.ext

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ephirium.coffee.core.navigation.components.NavComponent
import com.ephirium.coffee.core.navigation.components.NavDestination
import com.ephirium.coffee.core.navigation.components.NavInnerGraph

fun NavGraphBuilder.composable(
    navController: NavController,
    navDestination: NavDestination,
): Unit = navDestination.run {
    composable(
        route = route,
        arguments = arguments,
        deepLinks = deepLinks,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
    ) {
        destination(navController)
    }
}

fun NavGraphBuilder.navigation(
    navController: NavController,
    navInnerGraph: NavInnerGraph,
): Unit = navInnerGraph.run {
    navigation(
        route = route,
        startDestination = startComponent.route,
    ) {
        navComponents.forEach { navComponent ->
            navComponent(navController, navComponent)
        }
    }
}

fun NavGraphBuilder.navComponent(navController: NavController, navComponent: NavComponent): Unit =
    when (navComponent) {
        is NavDestination -> composable(navController, navComponent)
        is NavInnerGraph  -> navigation(navController, navComponent)
    }
