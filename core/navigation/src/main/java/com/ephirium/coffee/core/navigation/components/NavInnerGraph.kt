package com.ephirium.coffee.core.navigation.components

class NavInnerGraph internal constructor(
    override val route: String,
    val startComponent: NavComponent,
    val navComponents: List<NavComponent>,
) : NavComponent

