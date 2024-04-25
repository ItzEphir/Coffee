package com.ephirium.coffee.feature.compliment.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class ComplimentUiModel(val text: String) : Parcelable
