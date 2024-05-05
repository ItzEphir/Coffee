package com.ephirium.coffee.feature.compliment.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ephirium.coffee.core.result.ResponseResult.Failure.NoInternetError
import com.ephirium.coffee.core.result.ResponseResult.Failure.TimeoutError
import com.ephirium.coffee.core.result.on
import com.ephirium.coffee.core.result.onFailure
import com.ephirium.coffee.core.result.onOk
import com.ephirium.coffee.data.compliment.repository.ComplimentRepository
import com.ephirium.coffee.feature.compliment.presentation.event.ComplimentUiEvent
import com.ephirium.coffee.feature.compliment.presentation.event.ComplimentUiEvent.*
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
    
    private val event = MutableStateFlow<ComplimentUiEvent>(value = Loading)
    
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
                Log.d("ComplimentScreenViewModel", "UiEvent: $complimentUiEvent")
                when (complimentUiEvent) {
                    Loading          -> onLoading()
                    is Swap          -> onSwap()
                    AddButtonPressed -> onGoToAdd()
                    is Retry         -> onRetry()
                }
            }
        }
    }
    
    private fun onSwap() {
        (uiState.value as? ComplimentUiState.Compliment)?.let { complimentUiState ->
            viewModelScope.launch {
                complimentRepository.getRandomCompliment(TimeZone.currentSystemDefault())
                    .collectLatest { responseResult ->
                        responseResult.onOk { compliment ->
                            setUiState(complimentUiState.copy(complimentModel = compliment.toUi()))
                            return@collectLatest
                        }.on<NoInternetError> {
                            it.throwable.printStackTrace()
                            setUiState(ComplimentUiState.NoInternet)
                            return@collectLatest
                        }.on<TimeoutError> {
                            it.throwable.printStackTrace()
                            setUiState(ComplimentUiState.Timeout)
                            return@collectLatest
                        }.onFailure {
                            it.printStackTrace()
                            setUiState(ComplimentUiState.Error)
                            return@collectLatest
                        }
                    }
            }
        }
    }
    
    private fun onLoading() {
        setUiState(ComplimentUiState.Loading)
        viewModelScope.launch {
            complimentRepository.getRandomCompliment(TimeZone.currentSystemDefault())
                .collectLatest { responseResult ->
                    responseResult.onOk { compliment ->
                        setUiState(ComplimentUiState.Compliment(complimentModel = compliment.toUi()))
                    }.on<NoInternetError> {
                        it.throwable.printStackTrace()
                        setUiState(ComplimentUiState.NoInternet)
                        return@collectLatest
                    }.on<TimeoutError> {
                        it.throwable.printStackTrace()
                        setUiState(ComplimentUiState.Timeout)
                        return@collectLatest
                    }.onFailure {
                        it.printStackTrace()
                        setUiState(ComplimentUiState.Error)
                        return@collectLatest
                    }
                }
        }
    }
    
    private fun onGoToAdd() {
        setUiState(ComplimentUiState.AddClicked)
    }
    
    private fun onRetry(){
        passEvent(Loading)
    }
    
    companion object {
        private const val UI_STATE_KEY = "compliment_ui_state"
    }
}