package com.ephirium.coffee.feature.profile.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ephirium.coffee.core.result.onOk
import com.ephirium.coffee.data.compliment.repository.ComplimentRepository
import com.ephirium.coffee.feature.profile.presentation.event.ProfileUiEvent
import com.ephirium.coffee.feature.profile.presentation.event.ProfileUiEvent.*
import com.ephirium.coffee.feature.profile.presentation.mapper.ComplimentMapper.Companion.toUiModel
import com.ephirium.coffee.feature.profile.presentation.model.ProfileUiModel
import com.ephirium.coffee.feature.profile.presentation.model.UserInfoUiModel
import com.ephirium.coffee.feature.profile.presentation.state.ProfileDataState
import com.ephirium.coffee.feature.profile.presentation.state.ProfileUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

internal class ProfileScreenViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val complimentRepository: ComplimentRepository,
) : ViewModel() {
    
    val profileUiState =
        savedStateHandle.getStateFlow<ProfileUiState>(UI_STATE_KEY, ProfileUiState.Loading)
    
    private val dataState = savedStateHandle.getStateFlow(DATA_STATE_KEY, ProfileDataState.default)
    
    private val event = MutableStateFlow<ProfileUiEvent>(InitialLoading)
    
    init {
        onEvent()
    }
    
    fun passEvent(profileUiEvent: ProfileUiEvent) {
        viewModelScope.launch {
            event.emit(profileUiEvent)
        }
    }
    
    private fun setUiState(profileUiState: ProfileUiState) {
        savedStateHandle[UI_STATE_KEY] = profileUiState
    }
    
    private fun setDataState(profileDataState: ProfileDataState) {
        savedStateHandle[DATA_STATE_KEY] = profileDataState
    }
    
    private fun onEvent() {
        viewModelScope.launch {
            event.collectLatest { profileUiEvent ->
                when (profileUiEvent) {
                    InitialLoading -> onInitialLoading()
                    is Loading     -> onLoading(profileUiEvent.userLogin)
                    is Add         -> onAdd()
                    is Refresh     -> onRefresh(profileUiEvent.userLogin)
                }
            }
        }
    }
    
    private fun onInitialLoading() = Unit
    
    private fun onLoading(userLogin: String) {
        setUiState(ProfileUiState.Loading)
        setDataState(dataState.value.copy(userLogin = userLogin))
        viewModelScope.launch {
            complimentRepository.getComplimentsByAuthor(
                dataState.value.userLogin, dataState.value.currentPage
            ).collectLatest { responseResult ->
                responseResult.onOk { compliments ->
                    setDataState(dataState.value.copy(isEnd = compliments.isEnd))
                    setUiState(
                        ProfileUiState.Profile(
                            profileUiModel = ProfileUiModel(
                                userInfoUiModel = UserInfoUiModel(
                                    id = "",
                                    login = userLogin,
                                    name = "",
                                ),
                                compliments = compliments.compliments.map { it.toUiModel() },
                            )
                        )
                    )
                    return@collectLatest
                }
            }
        }
    }
    
    private fun onAdd() {
        (profileUiState.value as? ProfileUiState.Profile)?.let {
            if (!dataState.value.isEnd) {
                setDataState(dataState.value.copy(currentPage = dataState.value.currentPage + 1))
            }
            viewModelScope.launch {
                complimentRepository.getComplimentsByAuthor(
                    dataState.value.userLogin, dataState.value.currentPage
                ).collectLatest { responseResult ->
                    responseResult.onOk {
                    
                    }
                }
            }
        }
    }
    
    private fun onRefresh(userLogin: String) = onLoading(userLogin)
    
    private companion object {
        const val UI_STATE_KEY = "profile_ui_state"
        
        const val DATA_STATE_KEY = "profile_data_state"
    }
}