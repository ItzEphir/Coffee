package com.ephirium.coffee.feature.profile.presentation.state

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.ephirium.coffee.feature.profile.presentation.model.ProfileUiModel
import kotlinx.parcelize.Parcelize

@Immutable
internal sealed interface ProfileUiState : Parcelable {
    @Parcelize
    data object Loading : ProfileUiState
    
    @Parcelize
    data class Profile(val profileUiModel: ProfileUiModel) : ProfileUiState
    
    @Parcelize
    data object NoInternet : ProfileUiState
    
    @Parcelize
    data object Timeout : ProfileUiState
    
    @Parcelize
    data object InvalidResponse : ProfileUiState
    
    @Parcelize
    data object Error : ProfileUiState
}