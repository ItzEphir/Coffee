package com.ephirium.coffee.feature.compliment_editor.presentation.model

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
internal data class ComplimentUiModel(
    val id: String,
    val text: String,
    val languageCode: String,
    val authorId: String,
    val likedIds: List<String>,
) : Parcelable {
    companion object {
        val default = ComplimentUiModel(
            id = "id", text = "text",
            languageCode = "en",
            authorId = "id",
            likedIds = listOf(),
        )
    }
}