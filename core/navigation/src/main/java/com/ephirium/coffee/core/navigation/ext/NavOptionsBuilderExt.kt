package com.ephirium.coffee.core.navigation.ext

import androidx.navigation.NavOptionsBuilder
import androidx.navigation.PopUpToBuilder
import com.ephirium.coffee.core.navigation.components.NavComponent

fun NavOptionsBuilder.popUpTo(
    navComponent: NavComponent,
    popUpToBuilder: PopUpToBuilder.() -> Unit = {},
) = popUpTo(navComponent.route, popUpToBuilder)
