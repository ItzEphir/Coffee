package com.ephirium.coffee.feature.compliment.presentation.event

internal sealed interface ComplimentUiEvent {
    data object Loading : ComplimentUiEvent
    
    data object Swap : ComplimentUiEvent
}