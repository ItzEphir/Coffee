package com.ephirium.coffee.app.presentation.ui.components.screens.compliment

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.ephirium.coffee.app.presentation.state.MainScreenState.*
import com.ephirium.coffee.app.presentation.viewmodel.ComplimentScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ComplimentScreen(
    navController: NavController,
    viewModel: ComplimentScreenViewModel = koinViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    
    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            when (state) {
                is Loading -> Loading(onLaunch = { viewModel.loadCompliment() })
                is Error   -> Error()
                is Active  -> Active(state = state as Active, onSwapClicked = {
                    viewModel.swapCompliment()
                })
            }
        }
    }
}