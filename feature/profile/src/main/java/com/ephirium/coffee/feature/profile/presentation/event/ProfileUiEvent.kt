package com.ephirium.coffee.feature.profile.presentation.event

internal sealed interface ProfileUiEvent {
    data object InitialLoading: ProfileUiEvent
    
    data class Loading(
        val userLogin: String,
    ) : ProfileUiEvent
    
    @Suppress("CanSealedSubClassBeObject")
    class Add : ProfileUiEvent {
        override fun toString(): String = this::class.simpleName.toString()
    }
    
    data class Refresh(
        val userLogin: String,
    ) : ProfileUiEvent
}