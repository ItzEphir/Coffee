package com.ephirium.coffee.feature.profile.presentation.mapper

import com.ephirium.coffee.data.compliment.model.entity.Compliment
import com.ephirium.coffee.feature.profile.presentation.model.ComplimentUiModel

class ComplimentMapper {
    companion object {
        fun Compliment.toUiModel() = ComplimentUiModel(
            text = text,
            languageCode = language,
        )
    }
}