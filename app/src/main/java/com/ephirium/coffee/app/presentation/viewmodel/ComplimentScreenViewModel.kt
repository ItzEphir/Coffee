package com.ephirium.coffee.app.presentation.viewmodel

import android.util.Log
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
    private val getRandomCompliment: GetRandomComplimentUseCase,
    private val getSavedCompliment: GetSavedComplimentUseCase,
    private val saveComplimentId: SaveComplimentIdUseCase,
) : ViewModel() {
    val uiState: StateFlow<ComplimentScreenState> =
        savedStateHandle.getStateFlow<ComplimentScreenState>(
            uiStateKey, initialValue = Loading
        )
    
    fun loadCompliment() {
        viewModelScope.launch {
            getSavedCompliment().collectLatest { status ->
                when (status) {
                    is Success                    -> savedStateHandle[uiStateKey] =
                        Active(isVisible = true, compliment = status.result.mapWithUi())
                    
                    is Status.Error               -> {
                        swapCompliment()
                        Log.d("ComplimentViewModel", "Error")
                    }
                    
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
            getRandomCompliment(
                if (uiState.value is Active) (uiState.value as Active).compliment.id
                else null
            ).collectLatest { status ->
                when (status) {
                    is Success -> {
                        delay(Animations.complimentAnimationDuration)
                        saveComplimentId(status.result.id).collectLatest {
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
        private const val uiStateKey = "compliment_ui_state"
    }
}