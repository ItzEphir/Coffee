package com.ephirium.coffee.feature.compliment_editor.presentation.state

import android.os.Parcelable
import com.ephirium.coffee.feature.compliment_editor.presentation.model.ComplimentEditorUiModel
import com.ephirium.coffee.feature.compliment_editor.presentation.model.ComplimentUiModel
import kotlinx.parcelize.Parcelize

internal sealed interface ComplimentEditorUiState : Parcelable {
    @Parcelize
    data object Loading : ComplimentEditorUiState
    
    @Parcelize
    data class Editing(val complimentEditorUiModel: ComplimentEditorUiModel = ComplimentEditorUiModel.default) :
        ComplimentEditorUiState
    
    @Parcelize
    data object Publishing : ComplimentEditorUiState
    
    @Parcelize
    data class Published(val complimentUiModel: ComplimentUiModel) : ComplimentEditorUiState
    
    @Parcelize
    data object Timeout : ComplimentEditorUiState
    
    @Parcelize
    data object NoInternet : ComplimentEditorUiState
    
    @Parcelize
    data object Error : ComplimentEditorUiState
}