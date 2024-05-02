package com.ephirium.coffee.core.navigation.components

import androidx.compose.runtime.Composable

class NavInnerGraph internal constructor(
    override val route: String,
    val startComponent: NavComponent,
    val navComponents: List<NavComponent>,
    override val icon: @Composable () -> Unit = {},
    override val label: @Composable () -> Unit = {},
) : NavBarComponent

