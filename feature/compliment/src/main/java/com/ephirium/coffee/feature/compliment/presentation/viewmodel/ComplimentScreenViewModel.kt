package com.ephirium.coffee.feature.compliment.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ephirium.coffee.feature.compliment.presentation.event.ComplimentUiEvent
import com.ephirium.coffee.feature.compliment.presentation.state.ComplimentUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

internal class ComplimentScreenViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    
    val uiState = savedStateHandle.getStateFlow<ComplimentUiState>(
        key = UI_STATE_KEY,
        initialValue = ComplimentUiState.Loading,
    )
    
    private val event = MutableStateFlow<ComplimentUiEvent>(value = ComplimentUiEvent.Loading)
    
    init {
        onEvent()
    }
    
    fun passEvent(complimentUiEvent: ComplimentUiEvent) {
        viewModelScope.launch {
            event.emit(complimentUiEvent)
        }
    }
    
    private fun setUiState(complimentUiState: ComplimentUiState) {
        savedStateHandle[UI_STATE_KEY] = complimentUiState
    }
    
    private fun onEvent() {
        viewModelScope.launch {
            event.collectLatest { complimentUiEvent ->
                when (complimentUiEvent) {
                    ComplimentUiEvent.Loading -> onLoading()
                }
            }
        }
    }
    
    private fun onLoading(){
        setUiState(ComplimentUiState.Loading)
        viewModelScope.launch {
        
        }
    }
    
    companion object {
        private const val UI_STATE_KEY = "compliment_ui_state"
    }
}