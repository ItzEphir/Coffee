package com.ephirium.coffee.feature.compliment.presentation.model

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
internal data class ComplimentUiModel(val text: String) : Parcelable
