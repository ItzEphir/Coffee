package com.ephirium.coffee.core.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import com.ephirium.coffee.core.navigation.components.NavComponent
import com.ephirium.coffee.core.navigation.components.NavDestination

fun NavController.popBackStack(
    navComponent: NavComponent,
    inclusive: Boolean,
    saveState: Boolean = false,
) = popBackStack(navComponent.route, inclusive, saveState)

fun NavController.navigate(
    navComponent: NavDestination,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null,
) = navigate(navComponent.route, navOptions, navigatorExtras)