package com.ephirium.coffee.core.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.ephirium.coffee.core.navigation.components.NavComponent
import com.ephirium.coffee.core.navigation.components.NavDestination
import com.ephirium.coffee.core.navigation.components.NavInnerGraph

fun NavGraphBuilder.composable(
    navController: NavController,
    navDestination: NavDestination,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
) = composable(
    navDestination.route,
    arguments,
    deepLinks,
    navDestination.enterTransition,
    navDestination.exitTransition,
    navDestination.popEnterTransition,
    navDestination.popExitTransition,
) {
    navDestination.destination(navController)
}

fun NavGraphBuilder.navigation(
    navController: NavController,
    navInnerGraph: NavInnerGraph,
) = navigation(
    route = navInnerGraph.route, startDestination = navInnerGraph.startComponent.route
) {
    navInnerGraph.navComponents.forEach { navComponent ->
        navComponent(navController, navComponent)
    }
}

fun NavGraphBuilder.navComponent(navController: NavController, navComponent: NavComponent) {
    when (navComponent) {
        is NavDestination -> composable(navController, navComponent)
        is NavInnerGraph  -> navigation(navController, navComponent)
    }
}