package com.ephirium.coffee.app.ui.navScreens.mainGraph

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ephirium.coffee.app.ui.navigation.MainScreenGraph
import com.ephirium.coffee.core.navigation.components.NavBarComponent
import com.ephirium.coffee.core.navigation.ext.NavHost
import com.ephirium.coffee.core.navigation.ext.navComponent
import com.ephirium.coffee.core.navigation.ext.navigate
import com.ephirium.coffee.core.navigation.ext.popUpTo

@Composable
fun MainNavScreen(@Suppress("UNUSED_PARAMETER") navController: NavController) {
    val innerNavController = rememberNavController()
    
    val navComponents = MainScreenGraph.entries.map { it.navComponent }.filterIsInstance<NavBarComponent>()
    
    val navBackStateEntry by innerNavController.currentBackStackEntryAsState()
    val currentDestination by remember {
        derivedStateOf { navBackStateEntry?.destination }
    }
    
    Scaffold(bottomBar = {
        NavigationBar(
            modifier = Modifier.clip(RoundedCornerShape(32.dp)),
            containerColor = colorScheme.primaryContainer.copy(alpha = 0.5f),
        ) {
            navComponents.forEach { navComponent ->
                NavigationBarItem(
                    alwaysShowLabel = false,
                    selected = currentDestination?.route == navComponent.route,
                    onClick = {
                        innerNavController.navigate(navComponent) {
                            popUpTo(MainScreenGraph.COMPLIMENT.navComponent) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = navComponent.icon,
                    label = navComponent.label,
                )
            }
        }
    }) { paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = innerNavController,
            startComponent = MainScreenGraph.COMPLIMENT.navComponent
        ) {
            MainScreenGraph.entries.forEach { screen ->
                navComponent(innerNavController, screen.navComponent)
            }
        }
    }
}