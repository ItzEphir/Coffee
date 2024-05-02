package com.ephirium.coffee.feature.compliment.presentation.event

internal sealed interface ComplimentUiEvent {
    
    data object Loading : ComplimentUiEvent
    
    object Swap : ComplimentUiEvent{
        override fun equals(other: Any?): Boolean {
            return false
        }
        
        override fun toString(): String {
            return "Swap"
        }
    }
}