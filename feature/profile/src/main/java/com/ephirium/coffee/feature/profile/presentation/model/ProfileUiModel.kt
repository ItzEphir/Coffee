package com.ephirium.coffee.feature.profile.presentation.model

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
internal data class ProfileUiModel(
    val userInfoUiModel: UserInfoUiModel,
    val compliments: List<ComplimentUiModel>,
    val reachedEnd: Boolean = false,
): Parcelable
