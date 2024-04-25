package com.ephirium.coffee.feature.compliment.presentation.state

import android.os.Parcelable
import com.ephirium.coffee.feature.compliment.presentation.model.ComplimentUiModel
import kotlinx.parcelize.Parcelize

@Parcelize
internal sealed interface ComplimentUiState : Parcelable {
    @Parcelize
    data object Loading : ComplimentUiState
    
    @Parcelize
    data class Compliment(val complimentModel: ComplimentUiModel) : Parcelable
    
    
}