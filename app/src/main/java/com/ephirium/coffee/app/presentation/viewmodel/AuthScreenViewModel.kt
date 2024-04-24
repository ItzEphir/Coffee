package com.ephirium.coffee.app.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ephirium.coffee.app.presentation.state.AuthScreenState
import com.ephirium.coffee.app.presentation.state.AuthScreenState.SignIn
import com.ephirium.coffee.app.presentation.state.AuthScreenState.SignUp
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthScreenViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    
    val uiState: StateFlow<AuthScreenState> =
        savedStateHandle.getStateFlow(key = UI_STATE_KEY, initialValue = SignIn())
    
    fun signIn(onSuccess: () -> Unit, onFailure: () -> Unit) {
        if (uiState.value !is SignIn) return
        viewModelScope.launch {
        }
    }
    
    fun signUp(onSuccess: () -> Unit, onFailure: () -> Unit) {
        if (uiState.value !is SignUp) return
        viewModelScope.launch {
        }
    }
    
    fun goToSignUp() {
        savedStateHandle[UI_STATE_KEY] = SignUp(
            email = "", login = uiState.value.login, password = uiState.value.password
        )
    }
    
    fun goToSignIn() {
        savedStateHandle[UI_STATE_KEY] = SignIn(
            login = uiState.value.login, password = uiState.value.password
        )
    }
    
    fun changeEmail(email: String) {
        if (uiState.value is SignUp) {
            savedStateHandle[UI_STATE_KEY] = (uiState.value as SignUp).copy(email = email)
        }
    }
    
    fun changeLogin(login: String) {
        when (uiState.value) {
            is SignIn -> savedStateHandle[UI_STATE_KEY] =
                (uiState.value as SignIn).copy(login = login)
            
            is SignUp -> savedStateHandle[UI_STATE_KEY] =
                (uiState.value as SignUp).copy(login = login)
        }
    }
    
    fun changePassword(password: String) {
        when (uiState.value) {
            is SignIn -> savedStateHandle[UI_STATE_KEY] =
                (uiState.value as SignIn).copy(password = password)
            
            is SignUp -> savedStateHandle[UI_STATE_KEY] =
                (uiState.value as SignUp).copy(password = password)
        }
    }
    
    companion object {
        private const val UI_STATE_KEY = "auth_ui_state"
    }
}