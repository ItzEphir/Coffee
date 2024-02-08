package com.ephirium.coffee.app.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ephirium.coffee.app.preferences.PreferenceManager
import com.ephirium.coffee.app.presentation.model.mapWithUi
import com.ephirium.coffee.app.presentation.state.MainScreenState
import com.ephirium.coffee.app.presentation.state.MainScreenState.*
import com.ephirium.coffee.app.presentation.ui.theme.Animations
import com.ephirium.coffee.domain.usecase.GetComplimentByIdUseCase
import com.ephirium.coffee.domain.usecase.GetRandomComplimentUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ComplimentScreenViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getRandomComplimentUseCase: GetRandomComplimentUseCase,
    private val getComplimentByIdUseCase: GetComplimentByIdUseCase,
    private val preferenceManager: PreferenceManager,
) : ViewModel() {
    val uiState: StateFlow<MainScreenState> = savedStateHandle.getStateFlow<MainScreenState>(
        uiStateKey, initialValue = Loading
    )
    
    fun loadCompliment() {
        when (val id = preferenceManager.complimentId) {
            null -> swapCompliment()
            
            else -> viewModelScope.launch {
                getComplimentByIdUseCase.execute(id).catch {
                    savedStateHandle[uiStateKey] = Error
                }.collectLatest {
                    savedStateHandle[uiStateKey] = Active(isVisible = true, compliment = it.mapWithUi())
                }
            }
        }
    }
    
    fun swapCompliment() {
        viewModelScope.launch {
            if (uiState.value is Active) {
                savedStateHandle[uiStateKey] =
                    Active(isVisible = false, compliment = (uiState.value as Active).compliment)
            }
            getRandomComplimentUseCase.execute(
                if (uiState.value is Active) (uiState.value as Active).compliment.id else null
            ).catch {
                savedStateHandle[uiStateKey] = Error
            }.collectLatest {
                delay(Animations.complimentAnimationDuration)
                savedStateHandle[uiStateKey] = Active(isVisible = true, compliment = it.mapWithUi())
            }
        }
    }
    
    companion object {
        private const val uiStateKey = "ui_state"
    }
}