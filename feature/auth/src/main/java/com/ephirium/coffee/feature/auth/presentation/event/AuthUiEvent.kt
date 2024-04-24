package com.ephirium.coffee.feature.auth.presentation.event

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed interface AuthUiEvent: Parcelable {
    @Parcelize
    data object Loading : AuthUiEvent
    @Parcelize
    data object GoToSignIn : AuthUiEvent
    @Parcelize
    data object GoToSignUp : AuthUiEvent
    @Parcelize
    data object SignIn: AuthUiEvent
    @Parcelize
    data object SignUp: AuthUiEvent
    @Parcelize
    data class LoginChanged(val login: String) : AuthUiEvent
    @Parcelize
    data class PasswordChanged(val password: String) : AuthUiEvent
    @Parcelize
    data class NameChanged(val name: String) : AuthUiEvent
    @Parcelize
    data object CancelLoading: AuthUiEvent
    @Parcelize
    data object Retry: AuthUiEvent
}