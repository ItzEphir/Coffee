package com.ephirium.coffee.feature.profile.presentation.model

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class UserInfoUiModel(
    val id: String,
    val login: String,
    val name: String,
): Parcelable