package com.ephirium.coffee.feature.compliment_editor.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class ComplimentUiModel(
    val id: String,
    val text: String,
    val languageCode: String,
    val authorId: String,
    val likedIds: List<String>,
): Parcelable{
    companion object{
        val default = ComplimentUiModel(
            id = "id",
            text = "text",
            languageCode = "en",
            authorId = "id",
            likedIds = listOf()
        )
    }
}