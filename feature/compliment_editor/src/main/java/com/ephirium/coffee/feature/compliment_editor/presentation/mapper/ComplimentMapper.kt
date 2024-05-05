package com.ephirium.coffee.feature.compliment_editor.presentation.mapper

import com.ephirium.coffee.data.compliment.model.entity.Compliment
import com.ephirium.coffee.data.compliment.model.entity.ComplimentShort
import com.ephirium.coffee.feature.compliment_editor.presentation.model.ComplimentEditorUiModel
import com.ephirium.coffee.feature.compliment_editor.presentation.model.ComplimentUiModel

internal class ComplimentMapper {
    companion object {
        fun ComplimentEditorUiModel.toComplimentShort() = ComplimentShort(
            text = text,
            language = languageCode,
        )
        
        fun Compliment.toComplimentUiModel() = ComplimentUiModel(
            id = id,
            text = text,
            languageCode = language,
            authorId = authorId,
            likedIds = likedIds,
        )
    }
}