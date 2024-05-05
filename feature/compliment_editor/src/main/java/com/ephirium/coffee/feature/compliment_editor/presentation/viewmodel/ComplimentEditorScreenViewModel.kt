package com.ephirium.coffee.feature.compliment_editor.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ephirium.coffee.core.result.ResponseResult.Failure.*
import com.ephirium.coffee.core.result.on
import com.ephirium.coffee.core.result.onFailure
import com.ephirium.coffee.core.result.onOk
import com.ephirium.coffee.data.compliment.repository.ComplimentRepository
import com.ephirium.coffee.feature.compliment_editor.presentation.event.ComplimentEditorUiEvent
import com.ephirium.coffee.feature.compliment_editor.presentation.event.ComplimentEditorUiEvent.*
import com.ephirium.coffee.feature.compliment_editor.presentation.mapper.ComplimentMapper.Companion.toComplimentShort
import com.ephirium.coffee.feature.compliment_editor.presentation.mapper.ComplimentMapper.Companion.toComplimentUiModel
import com.ephirium.coffee.feature.compliment_editor.presentation.state.ComplimentEditorUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

internal class ComplimentEditorScreenViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val complimentRepository: ComplimentRepository,
) : ViewModel() {
    
    val complimentEditorUiState = savedStateHandle.getStateFlow<ComplimentEditorUiState>(
        UI_STATE_KEY, ComplimentEditorUiState.Loading
    )
    
    private val event = MutableStateFlow<ComplimentEditorUiEvent>(Loading)
    
    init {
        onEvent()
    }
    
    fun passEvent(complimentEditorUiEvent: ComplimentEditorUiEvent) {
        viewModelScope.launch {
            event.emit(complimentEditorUiEvent)
        }
    }
    
    private fun setUiState(complimentEditorUiState: ComplimentEditorUiState) {
        savedStateHandle[UI_STATE_KEY] = complimentEditorUiState
    }
    
    private fun onEvent() {
        viewModelScope.launch {
            event.collectLatest { uiEvent ->
                when (uiEvent) {
                    Loading                -> onLoading()
                    is TextChanged         -> onTextChanged(uiEvent.text)
                    is LanguageCodeChanged -> onLanguageCodeChanged(uiEvent.languageCode)
                    is Retry               -> onRetry()
                    Publish                -> onPublish()
                }
            }
        }
    }
    
    private fun onLoading() {
        setUiState(ComplimentEditorUiState.Loading)
        setUiState(ComplimentEditorUiState.Editing())
    }
    
    private fun onTextChanged(text: String) {
        (complimentEditorUiState.value as? ComplimentEditorUiState.Editing)?.let { editing ->
            setUiState(
                editing.copy(
                    complimentEditorUiModel = editing.complimentEditorUiModel.copy(
                        text = text
                    )
                )
            )
        }
    }
    
    private fun onLanguageCodeChanged(languageCode: String) {
        (complimentEditorUiState.value as? ComplimentEditorUiState.Editing)?.let { editing ->
            setUiState(
                editing.copy(
                    complimentEditorUiModel = editing.complimentEditorUiModel.copy(
                        languageCode = languageCode
                    )
                )
            )
        }
    }
    
    private fun onRetry() = onLoading()
    
    private fun onPublish() {
        (complimentEditorUiState.value as? ComplimentEditorUiState.Editing)?.let { editing ->
            viewModelScope.launch {
                setUiState(ComplimentEditorUiState.Publishing)
                complimentRepository.createCompliment(editing.complimentEditorUiModel.toComplimentShort())
                    .collectLatest { responseResult ->
                        responseResult.onOk { compliment ->
                            setUiState(ComplimentEditorUiState.Published(compliment.toComplimentUiModel()))
                            return@collectLatest
                        }.on<TimeoutError>{
                            it.throwable.printStackTrace()
                            setUiState(ComplimentEditorUiState.Timeout)
                            return@collectLatest
                        }.on<HttpResponseFailure> {
                            it.throwable.printStackTrace()
                            setUiState(ComplimentEditorUiState.Error)
                            return@collectLatest
                        }.on<NoInternetError> {
                            it.throwable.printStackTrace()
                            setUiState(ComplimentEditorUiState.NoInternet)
                            return@collectLatest
                        }.onFailure { throwable ->
                            throwable.printStackTrace()
                            setUiState(ComplimentEditorUiState.Error)
                            return@collectLatest
                        }
                    }
            }
        }
    }
    
    private companion object {
        private const val UI_STATE_KEY = "compliment_editor_ui_state"
    }
}