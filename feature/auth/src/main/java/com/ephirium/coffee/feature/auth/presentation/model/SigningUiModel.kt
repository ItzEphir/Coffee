package com.ephirium.coffee.feature.auth.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SigningUiModel(
    val login: String,
    val password: String,
    val authorizeState: AuthorizeState
): Parcelable {
    sealed interface AuthorizeState: Parcelable {
        @Parcelize
        data object In: AuthorizeState
        @Parcelize
        data class Up(val name: String): AuthorizeState
    }
}
