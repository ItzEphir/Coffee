package com.ephirium.coffee.core.navigation.ext

import com.ephirium.coffee.core.navigation.components.NavComponent
import com.ephirium.coffee.core.navigation.components.NavInnerGraph

fun NavInnerGraph(
    route: String,
    startComponent: NavComponent,
    additionalComponents: List<NavComponent>,
) = NavInnerGraph(
    route = route,
    startComponent = startComponent,
    navComponents = listOf(startComponent) + additionalComponents,
)