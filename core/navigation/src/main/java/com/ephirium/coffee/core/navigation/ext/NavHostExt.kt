package com.ephirium.coffee.core.navigation.ext

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ephirium.coffee.core.navigation.components.NavComponent
import com.ephirium.coffee.core.navigation.components.NavDestination
import com.ephirium.coffee.core.navigation.components.NavInnerGraph

@Composable
fun NavHost(
    navController: NavHostController,
    startComponent: NavComponent,
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.Center,
    route: String? = null,
    enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) = {
        fadeIn(animationSpec = tween(700))
    },
    exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) = {
        fadeOut(animationSpec = tween(700))
    },
    popEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) = enterTransition,
    popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) = exitTransition,
    builder: NavGraphBuilder.() -> Unit,
) = NavHost(
    navController,
    remember(route, startComponent.route, builder) {
        navController.createGraph(startComponent.route, route, builder)
    },
    modifier,
    contentAlignment,
    enterTransition,
    exitTransition,
    popEnterTransition,
    popExitTransition,
)


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
