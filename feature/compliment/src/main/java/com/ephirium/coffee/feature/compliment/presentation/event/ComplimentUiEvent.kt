package com.ephirium.coffee.feature.compliment.presentation.event

internal sealed interface ComplimentUiEvent {
    
    data object Loading : ComplimentUiEvent
    
    @Suppress("CanSealedSubClassBeObject")
    class Swap : ComplimentUiEvent {
        override fun toString(): String = this::class.simpleName.toString()
    }
    
    data object AddButtonPressed : ComplimentUiEvent
    
    @Suppress("CanSealedSubClassBeObject")
    class Retry : ComplimentUiEvent {
        override fun toString(): String = this::class.simpleName.toString()
    }
}