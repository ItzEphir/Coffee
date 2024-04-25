package com.ephirium.coffee.feature.compliment.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ephirium.coffee.feature.compliment.presentation.state.ComplimentUiState
import com.ephirium.coffee.feature.compliment.presentation.viewmodel.ComplimentScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ComplimentScreen() {
    val viewModel: ComplimentScreenViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    
    ComplimentScreenLayout(uiState)
}

@Composable
private fun ComplimentScreenLayout(uiState: ComplimentUiState){
    
}

@Preview
@Composable
fun ComplimentScreenPreview() {
}