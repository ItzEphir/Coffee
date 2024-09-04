package com.ephirium.coffee.feature.auth.presentation.model

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.ephirium.coffee.feature.auth.presentation.model.SigningUiModel.AuthorizeState.In
import com.ephirium.coffee.feature.auth.presentation.model.SigningUiModel.AuthorizeState.Up
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
internal data class SigningUiModel(
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
    companion object{
        val defaultIn = SigningUiModel("", "", In)
        val defaultUp = SigningUiModel("", "", Up(""))
    }
}
