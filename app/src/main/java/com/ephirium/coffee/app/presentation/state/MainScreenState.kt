package com.ephirium.coffee.app.presentation.state

import android.os.Parcelable
import com.ephirium.coffee.app.presentation.model.UiCompliment
import kotlinx.parcelize.Parcelize

sealed interface MainScreenState : Parcelable {
    
    @Parcelize
    data object Loading : MainScreenState
    
    @Parcelize
    data object Error: MainScreenState
    
    @Parcelize
    data class Active(val isVisible: Boolean, val compliment: UiCompliment) : MainScreenState
    
}