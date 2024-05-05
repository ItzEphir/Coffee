package com.ephirium.coffee.app.ui.navScreens.mainScreenGraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.ephirium.coffee.app.ui.navigation.MainScreenGraph
import com.ephirium.coffee.core.navigation.ext.navigate
import com.ephirium.coffee.feature.compliment_editor.ui.screen.ComplimentEditorScreen

@Composable
fun AddNavScreen(navController: NavController){
    ComplimentEditorScreen(onPushed = {
        navController.navigate(MainScreenGraph.COMPLIMENT.navComponent)
    })
}