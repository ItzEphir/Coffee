package com.ephirium.coffee.feature.compliment_editor.presentation.model

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
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
