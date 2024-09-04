package com.ephirium.coffee.feature.compliment.presentation.state

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.ephirium.coffee.feature.compliment.presentation.model.ComplimentUiModel
import kotlinx.parcelize.Parcelize

@Immutable
internal sealed interface ComplimentUiState : Parcelable {
    @Parcelize
    data object Loading : ComplimentUiState
    
    @Parcelize
    data class Compliment(val complimentModel: ComplimentUiModel) : ComplimentUiState
    
    @Parcelize
    data object AddClicked: ComplimentUiState
    
    @Parcelize
    data object NoInternet: ComplimentUiState
    
    @Parcelize
    data object Timeout: ComplimentUiState
    
    @Parcelize
    data object Error: ComplimentUiState
}