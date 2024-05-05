package com.ephirium.coffee.feature.compliment_editor.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class ComplimentEditorUiModel(
    val text: String,
    val languageCode: String,
) : Parcelable {
    companion object {
        val default = ComplimentEditorUiModel(
            text = "",
            languageCode = "en",
        )
    }
}
