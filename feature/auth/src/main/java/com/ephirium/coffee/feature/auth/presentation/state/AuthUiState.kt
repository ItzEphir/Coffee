package com.ephirium.coffee.feature.auth.presentation.state

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.ephirium.coffee.feature.auth.presentation.model.SigningUiModel
import kotlinx.parcelize.Parcelize

@Immutable
internal sealed interface AuthUiState : Parcelable {
    @Parcelize
    data object Loading : AuthUiState
    
    @Parcelize
    data object PartialLoading : AuthUiState
    
    @Parcelize
    data class Signing(
        val signingUiModel: SigningUiModel,
    ) : AuthUiState
    
    @Parcelize
    data object Timeout : AuthUiState
    
    @Parcelize
    data object NoInternet : AuthUiState
    
    @Parcelize
    data object Error : AuthUiState
    
    @Parcelize
    data object Authorized : AuthUiState
    
    companion object{
        val default = Loading
    }
}