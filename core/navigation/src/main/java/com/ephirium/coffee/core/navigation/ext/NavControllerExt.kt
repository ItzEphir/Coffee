package com.ephirium.coffee.core.navigation.ext

import androidx.navigation.*
import com.ephirium.coffee.core.navigation.components.NavComponent

fun NavController.popBackStack(
    navComponent: NavComponent,
    inclusive: Boolean,
    saveState: Boolean = false,
) = popBackStack(navComponent.route, inclusive, saveState)

fun NavController.navigate(
    navComponent: NavComponent,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null,
) = navigate(navComponent.route, navOptions, navigatorExtras)

fun NavController.navigate(navComponent: NavComponent, builder: NavOptionsBuilder.() -> Unit) =
    navigate(navComponent, navOptions(builder))