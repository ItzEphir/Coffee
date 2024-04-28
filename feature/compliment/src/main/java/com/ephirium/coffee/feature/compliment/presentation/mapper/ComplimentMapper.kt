package com.ephirium.coffee.feature.compliment.presentation.mapper

import com.ephirium.coffee.data.compliment.model.entity.Compliment
import com.ephirium.coffee.feature.compliment.presentation.model.ComplimentUiModel

internal class ComplimentMapper {
    companion object {
        fun Compliment.toUi() = ComplimentUiModel(
            text = text
        )
    }
}