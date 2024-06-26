package com.ephirium.coffee.app.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.ui.res.painterResource
import com.ephirium.coffee.app.R.drawable
import com.ephirium.coffee.app.ui.navScreens.mainScreenGraph.AddNavScreen
import com.ephirium.coffee.app.ui.navScreens.mainScreenGraph.ComplimentNavScreen
import com.ephirium.coffee.core.navigation.components.NavComponent
import com.ephirium.coffee.core.navigation.components.NavDestination

enum class MainScreenGraph(val navComponent: NavComponent) {
    COMPLIMENT(
        navComponent = NavDestination(
            route = "compliment",
            destination = { navController ->
                ComplimentNavScreen(navController)
            },
            icon = {
                Icon(
                    painter = painterResource(id = drawable.smile),
                    contentDescription = null,
                    tint = colorScheme.primary,
                )
            },
            label = {
                Text(
                    text = "compliment",
                    style = typography.bodyMedium,
                )
            },
        )
    ),
    Add(
        navComponent = NavDestination(
            route = "add",
            destination = { navController ->
                AddNavScreen(navController)
            },
            icon = {
                Icon(
                    imageVector = Icons.Filled.AddCircle,
                    contentDescription = null,
                    tint = colorScheme.primary,
                )
            },
            label = {
                Text(
                    text = "add",
                    style = typography.bodyMedium
                )
            },
        ),
    )
}