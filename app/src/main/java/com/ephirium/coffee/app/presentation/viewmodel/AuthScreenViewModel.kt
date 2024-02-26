package com.ephirium.coffee.app.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ephirium.coffee.app.presentation.state.AuthScreenState
import com.ephirium.coffee.app.presentation.state.AuthScreenState.SignIn
import com.ephirium.coffee.app.presentation.state.AuthScreenState.SignUp
import com.ephirium.coffee.common.onFailure
import com.ephirium.coffee.common.onSuccess
import com.ephirium.coffee.domain.usecase.user.SignInByLoginUseCase
import com.ephirium.coffee.domain.usecase.user.SignUpUseCase
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AuthScreenViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val signUp: SignUpUseCase,
    private val signInByLogin: SignInByLoginUseCase,
) : ViewModel() {
    
    val uiState: StateFlow<AuthScreenState> =
        savedStateHandle.getStateFlow(key = uiStateKey, initialValue = SignIn())
    
    fun signIn(onSuccess: () -> Unit, onFailure: () -> Unit) {
        if (uiState.value !is SignIn) return
        viewModelScope.launch {
            signInByLogin(
                login = uiState.value.login,
                password = uiState.value.password
            ).collectLatest {
                it.onSuccess { onSuccess() }.onFailure { onFailure() }
            }
        }
    }
    
    fun signUp(onSuccess: () -> Unit, onFailure: () -> Unit) {
        if (uiState.value !is SignUp) return
        viewModelScope.launch {
            signUp(
                login = uiState.value.login,
                password = uiState.value.password,
                email = (uiState.value as SignUp).email
            ).collectLatest { result ->
                result.onSuccess { onSuccess() }.onFailure { onFailure() }
            }
        }
    }
    
    fun goToSignUp() {
        savedStateHandle[uiStateKey] = SignUp(
            email = "", login = uiState.value.login, password = uiState.value.password
        )
    }
    
    fun goToSignIn() {
        savedStateHandle[uiStateKey] = SignIn(
            login = uiState.value.login, password = uiState.value.password
        )
    }
    
    fun changeEmail(email: String) {
        if (uiState.value is SignUp) {
            savedStateHandle[uiStateKey] = (uiState.value as SignUp).copy(email = email)
        }
    }
    
    fun changeLogin(login: String) {
        when (uiState.value) {
            is SignIn -> savedStateHandle[uiStateKey] =
                (uiState.value as SignIn).copy(login = login)
            
            is SignUp -> savedStateHandle[uiStateKey] =
                (uiState.value as SignUp).copy(login = login)
        }
    }
    
    fun changePassword(password: String) {
        when (uiState.value) {
            is SignIn -> savedStateHandle[uiStateKey] =
                (uiState.value as SignIn).copy(password = password)
            
            is SignUp -> savedStateHandle[uiStateKey] =
                (uiState.value as SignUp).copy(password = password)
        }
    }
    
    companion object {
        private const val uiStateKey = "auth_ui_state"
    }
}