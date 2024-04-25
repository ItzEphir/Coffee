package com.ephirium.coffee.feature.auth.presentation.event

internal sealed interface AuthUiEvent {
    data object Loading : AuthUiEvent
    data object GoToSignIn : AuthUiEvent
    data object GoToSignUp : AuthUiEvent
    data object SignIn: AuthUiEvent
    data object SignUp: AuthUiEvent
    data class LoginChanged(val login: String) : AuthUiEvent
    data class PasswordChanged(val password: String) : AuthUiEvent
    data class NameChanged(val name: String) : AuthUiEvent
    data object CancelLoading: AuthUiEvent
    data object Retry: AuthUiEvent
    
    companion object{
        val default = Loading
    }
}