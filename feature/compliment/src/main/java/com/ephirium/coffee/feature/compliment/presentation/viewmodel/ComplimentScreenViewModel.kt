package com.ephirium.coffee.feature.compliment.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ephirium.coffee.core.result.onOk
import com.ephirium.coffee.data.compliment.repository.ComplimentRepository
import com.ephirium.coffee.feature.compliment.presentation.event.ComplimentUiEvent
import com.ephirium.coffee.feature.compliment.presentation.mapper.ComplimentMapper.Companion.toUi
import com.ephirium.coffee.feature.compliment.presentation.state.ComplimentUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.datetime.TimeZone

internal class ComplimentScreenViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val complimentRepository: ComplimentRepository,
) : ViewModel() {
    
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
                println(complimentUiEvent)
                when (complimentUiEvent) {
                    ComplimentUiEvent.Loading -> onLoading()
                    is ComplimentUiEvent.Swap    -> onSwap()
                }
                println(complimentUiEvent)
            }
        }
    }
    
    private fun onSwap() {
        (uiState.value as? ComplimentUiState.Compliment)?.let { complimentUiState ->
            viewModelScope.launch {
                complimentRepository.getRandomCompliment(TimeZone.currentSystemDefault()).collectLatest { responseResult ->
                        responseResult.onOk { compliment ->
                            setUiState(complimentUiState.copy(complimentModel = compliment.toUi()))
                        }
                    }
            }
        }
    }
    
    private fun onLoading() {
        println("GO")
        setUiState(ComplimentUiState.Loading)
        viewModelScope.launch {
            complimentRepository.getRandomCompliment(TimeZone.currentSystemDefault())
                .collectLatest { responseResult ->
                    responseResult.onOk { compliment ->
                        setUiState(ComplimentUiState.Compliment(complimentModel = compliment.toUi()))
                    }
                }
        }
    }
    
    companion object {
        private const val UI_STATE_KEY = "compliment_ui_state"
    }
}