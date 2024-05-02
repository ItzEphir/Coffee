package com.ephirium.coffee.core.navigation.components

import androidx.compose.runtime.Composable

sealed interface NavBarComponent : NavComponent {
    val label: @Composable () -> Unit
    val icon: @Composable () -> Unit
}