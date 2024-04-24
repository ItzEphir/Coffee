package com.ephirium.coffee.app.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UiCompliment(val id: String, val text: Map<String, String>) : Parcelable