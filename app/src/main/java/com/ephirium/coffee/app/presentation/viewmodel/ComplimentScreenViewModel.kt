package com.ephirium.coffee.app.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ephirium.coffee.app.presentation.state.ComplimentScreenState
import com.ephirium.coffee.app.presentation.state.ComplimentScreenState.Active
import com.ephirium.coffee.app.presentation.state.ComplimentScreenState.Loading
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ComplimentScreenViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val uiState: StateFlow<ComplimentScreenState> =
        savedStateHandle.getStateFlow<ComplimentScreenState>(
            UI_STATE_KEY, initialValue = Loading
        )
    
    fun loadCompliment() {
        viewModelScope.launch {
        }
    }
    
    fun swapCompliment() {
        viewModelScope.launch {
            if (uiState.value is Active) {
                savedStateHandle[UI_STATE_KEY] = (uiState.value as Active).copy(isVisible = false)
            }
            
        }
    }
    
    companion object {
        private const val UI_STATE_KEY = "compliment_ui_state"
    }
}