package com.ephirium.coffee.app.ui.navScreens.mainGraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.ephirium.coffee.app.ui.navigation.MainGraph.AUTH
import com.ephirium.coffee.app.ui.navigation.MainGraph.MAIN
import com.ephirium.coffee.core.navigation.ext.navigate
import com.ephirium.coffee.core.navigation.ext.popUpTo
import com.ephirium.coffee.feature.auth.ui.screen.AuthScreen

@Composable
fun AuthNavScreen(navController: NavController) {
    AuthScreen(onAuthorized = {
        navController.navigate(MAIN.navComponent){
            popUpTo(AUTH.navComponent){
                inclusive = true
                saveState = true
            }
        }
    })
}