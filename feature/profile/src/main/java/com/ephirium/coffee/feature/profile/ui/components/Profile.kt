package com.ephirium.coffee.feature.profile.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.ephirium.coffee.feature.profile.presentation.model.ProfileUiModel
import com.ephirium.coffee.preview.ThemePreview
import com.ephirium.coffee.theme.CoffeeTheme

internal data class ProfileActions(
    val onRefresh: suspend () -> Unit,
    val onAdd: suspend () -> Unit,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Profile(profileUiModel: ProfileUiModel, profileActions: ProfileActions) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MediumTopAppBar(
                title = { Text(text = profileUiModel.userInfoUiModel.login) },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { paddingValues ->
        val isEmpty by remember {
            derivedStateOf {
                profileUiModel.compliments.isEmpty()
            }
        }
        when (isEmpty) {
            true -> Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                Text(
                    text = "No compliments found by this user",
                    modifier = Modifier.align(Alignment.Center),
                )
            }
            
            false -> Compliments(
                compliments = profileUiModel.compliments,
                onRefresh = profileActions.onRefresh,
                onAdd = profileActions.onAdd,
                modifier = Modifier.padding(paddingValues),
            )
        }
    }
}

private class ProfileUiModelProvider : PreviewParameterProvider<ProfileUiModel> {
    override val values: Sequence<ProfileUiModel> = FAKE_PROFILES
}

@ThemePreview
@Composable
private fun ProfilePreview(@PreviewParameter(ProfileUiModelProvider::class) profileUiModel: ProfileUiModel) {
    CoffeeTheme {
        Surface(color = colorScheme.background) {
            Profile(
                profileUiModel = profileUiModel,
                profileActions = ProfileActions({}, {}),
            )
        }
    }
}
