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
            uiStateKey, initialValue = Loading
        )
    
    fun loadCompliment() {
        viewModelScope.launch {
        }
    }
    
    fun swapCompliment() {
        viewModelScope.launch {
            if (uiState.value is Active) {
                savedStateHandle[uiStateKey] = (uiState.value as Active).copy(isVisible = false)
            }
            
        }
    }
    
    companion object {
        private const val uiStateKey = "compliment_ui_state"
    }
}