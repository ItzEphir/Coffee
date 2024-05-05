package com.ephirium.coffee.feature.compliment_editor.presentation.event

sealed interface ComplimentEditorUiEvent {
    data object Loading : ComplimentEditorUiEvent
    data class TextChanged(val text: String) : ComplimentEditorUiEvent
    data class LanguageCodeChanged(val languageCode: String) : ComplimentEditorUiEvent
    data object Publish: ComplimentEditorUiEvent
    @Suppress("CanSealedSubClassBeObject")
    class Retry(): ComplimentEditorUiEvent{
        override fun toString(): String = this::class.simpleName.toString()
    }
}