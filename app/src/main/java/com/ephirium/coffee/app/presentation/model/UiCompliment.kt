package com.ephirium.coffee.app.presentation.model

import android.os.Parcelable
import com.ephirium.coffee.domain.model.present.Compliment
import kotlinx.parcelize.Parcelize

@Parcelize
data class UiCompliment(val id: String, val text: Map<String, String>) : Parcelable

fun Compliment.mapWithUi(): UiCompliment = UiCompliment(id, text)