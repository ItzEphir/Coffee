package com.ephirium.coffee.feature.profile.ui.screen

import com.ephirium.coffee.feature.profile.presentation.model.ComplimentUiModel
import com.ephirium.coffee.feature.profile.presentation.model.ProfileUiModel
import com.ephirium.coffee.feature.profile.presentation.model.UserInfoUiModel
import com.ephirium.coffee.feature.profile.presentation.state.ProfileUiState
import com.ephirium.coffee.feature.profile.presentation.state.ProfileUiState.Loading
import com.ephirium.coffee.feature.profile.presentation.state.ProfileUiState.Profile

@Suppress("SpellCheckingInspection")
internal val FAKE_UI_STATES: Sequence<ProfileUiState> = sequenceOf(
    Loading,
    Profile(
        profileUiModel = ProfileUiModel(
            userInfoUiModel = UserInfoUiModel(
                id = "qwertyui",
                name = "qwerty",
                login = "qwertyuiop",
            ),
            compliments = emptyList(),
        ),
    ),
    Profile(
        profileUiModel = ProfileUiModel(
            userInfoUiModel = UserInfoUiModel(
                id = "qwertyui",
                name = "qwerty",
                login = "qwertyuiop",
            ),
            compliments = listOf(
                ComplimentUiModel(
                    text = "qwertyui",
                    languageCode = "qw",
                ),
            ),
        ),
    ),
    Profile(
        profileUiModel = ProfileUiModel(
            userInfoUiModel = UserInfoUiModel(
                id = "qwertyui",
                name = "qwerty",
                login = "qwertyuiop",
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
            ),
        ),
    ),
    Profile(
        profileUiModel = ProfileUiModel(
            userInfoUiModel = UserInfoUiModel(
                id = "qwertyui",
                name = "qwerty",
                login = "qwertyuiop",
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
    ),
)