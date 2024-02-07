package com.ephirium.coffee.app.presentation.state

import android.os.Parcelable
import com.ephirium.coffee.domain.model.present.Compliment
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import kotlinx.serialization.Serializable

@Serializable
sealed interface MainScreenState : Parcelable {
    
    @Serializable
    @Parcelize
    data object Loading : MainScreenState

    @Serializable
    @Parcelize
    data object Error: MainScreenState
    
    @Serializable
    @Parcelize
    data class Active(val isVisible: Boolean, val compliment: @RawValue Compliment) : MainScreenState
    
}