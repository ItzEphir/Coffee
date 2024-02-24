package com.ephirium.coffee.app.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ephirium.coffee.app.presentation.model.mapWithUi
import com.ephirium.coffee.app.presentation.state.ComplimentScreenState
import com.ephirium.coffee.app.presentation.state.ComplimentScreenState.*
import com.ephirium.coffee.app.presentation.state.ComplimentScreenState.Error
import com.ephirium.coffee.app.presentation.ui.theme.Animations
import com.ephirium.coffee.common.Status
import com.ephirium.coffee.common.Status.*
import com.ephirium.coffee.domain.usecase.compliment.GetRandomComplimentUseCase
import com.ephirium.coffee.domain.usecase.compliment.GetSavedComplimentUseCase
import com.ephirium.coffee.domain.usecase.compliment.SaveComplimentIdUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ComplimentScreenViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getRandomComplimentUseCase: GetRandomComplimentUseCase,
    private val getSavedComplimentUseCase: GetSavedComplimentUseCase,
    private val saveComplimentIdUseCase: SaveComplimentIdUseCase,
) : ViewModel() {
    val uiState: StateFlow<ComplimentScreenState> =
        savedStateHandle.getStateFlow<ComplimentScreenState>(
            uiStateKey, initialValue = Loading
        )
    
    fun loadCompliment() {
        viewModelScope.launch {
            getSavedComplimentUseCase.execute().collectLatest { status ->
                when (status) {
                    is Success                    -> savedStateHandle[uiStateKey] =
                        Active(isVisible = true, compliment = status.result.mapWithUi())
                    
                    is Status.Error               -> swapCompliment()
                    is NetworkError, TimeoutError -> savedStateHandle[uiStateKey] = Error
                }
            }
        }
    }
    
    fun swapCompliment() {
        viewModelScope.launch {
            if (uiState.value is Active) {
                savedStateHandle[uiStateKey] = (uiState.value as Active).copy(isVisible = false)
            }
            getRandomComplimentUseCase.execute(
                if (uiState.value is Active) (uiState.value as Active).compliment.id
                else null
            ).collectLatest { status ->
                when (status) {
                    is Success -> {
                        delay(Animations.complimentAnimationDuration)
                        saveComplimentIdUseCase.execute(status.result.id).collectLatest {
                            savedStateHandle[uiStateKey] =
                                Active(isVisible = false, compliment = status.result.mapWithUi())
                        }
                        
                    }
                    
                    else       -> savedStateHandle[uiStateKey] = Error
                }
            }
        }
    }
    
    companion object {
        private const val uiStateKey = "ui_state"
    }
}