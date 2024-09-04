package com.ephirium.coffee.feature.profile.ui.components

import com.ephirium.coffee.feature.profile.presentation.model.ComplimentUiModel
import com.ephirium.coffee.feature.profile.presentation.model.ProfileUiModel
import com.ephirium.coffee.feature.profile.presentation.model.UserInfoUiModel


@Suppress("SpellCheckingInspection")
internal val FAKE_PROFILES: Sequence<ProfileUiModel> = sequenceOf(
    ProfileUiModel(
        userInfoUiModel = UserInfoUiModel(
            id = "1234567890-",
            login = "qwertyuiop",
            name = "asdfghjkl",
        ),
        compliments = emptyList(),
    ),
    ProfileUiModel(
        userInfoUiModel = UserInfoUiModel(
            id = "1234567890-",
            login = "qwertyuiop",
            name = "asdfghjkl",
        ),
        compliments = listOf(
            ComplimentUiModel(
                text = "qwertyui",
                languageCode = "qw",
            ),
        ),
    ),
    ProfileUiModel(
        userInfoUiModel = UserInfoUiModel(
            id = "1234567890-",
            login = "qwertyuiop",
            name = "asdfghjkl",
        ),
        compliments = listOf(
            ComplimentUiModel(
                text = "qwertyui",
                languageCode = "qw",
            ),
            ComplimentUiModel(
                text = "qwertyui",
                languageCode = "qw",
            ),
            ComplimentUiModel(
                text = "qwertyui",
                languageCode = "qw",
            ),
            ComplimentUiModel(
                text = "qwertyui",
                languageCode = "qw",
            ),
            ComplimentUiModel(
                text = "qwertyui",
                languageCode = "qw",
            ),
            ComplimentUiModel(
                text = "qwertyui",
                languageCode = "qw",
            ),
            ComplimentUiModel(
                text = "qwertyui",
                languageCode = "qw",
            ),
            ComplimentUiModel(
                text = "qwertyui",
                languageCode = "qw",
            ),
            ComplimentUiModel(
                text = "qwertyui",
                languageCode = "qw",
            ),
            ComplimentUiModel(
                text = "qwertyui",
                languageCode = "qw",
            ),
        ),
    ),
)