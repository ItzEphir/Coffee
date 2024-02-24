package com.ephirium.coffee.app.presentation.state

import android.os.Parcelable
import com.ephirium.coffee.app.presentation.model.UiCompliment
import kotlinx.parcelize.Parcelize

sealed interface ComplimentScreenState : Parcelable {
    
    @Parcelize
    data object Loading : ComplimentScreenState
    
    @Parcelize
    data object Error: ComplimentScreenState
    
    @Parcelize
    data class Active(val isVisible: Boolean, val compliment: UiCompliment) : ComplimentScreenState
    
}