package com.ephirium.coffee.feature.profile.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ephirium.coffee.feature.profile.presentation.state.ProfileUiState
import com.ephirium.coffee.feature.profile.presentation.state.ProfileUiState.*
import com.ephirium.coffee.feature.profile.presentation.viewmodel.ProfileScreenViewModel
import com.ephirium.coffee.feature.profile.ui.components.Profile
import com.ephirium.coffee.feature.profile.ui.components.ProfileActions
import com.ephirium.coffee.preview.ThemePreview
import com.ephirium.coffee.theme.CoffeeTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen() {
    val viewModel: ProfileScreenViewModel = koinViewModel()
    val uiState by viewModel.profileUiState.collectAsStateWithLifecycle()
    
    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
        ProfileScreenLayout(
            uiState = uiState,
            profileScreenActions = ProfileScreenActions(
                onRefresh = {},
                onAdd = {},
            ),
            modifier = Modifier.padding(paddingValues),
        )
    }
}

private data class ProfileScreenActions(
    val onRefresh: suspend () -> Unit,
    val onAdd: suspend () -> Unit,
)

@Composable
private fun ProfileScreenLayout(
    uiState: ProfileUiState,
    profileScreenActions: ProfileScreenActions,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        when (uiState) {
            Loading         -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            
            is Profile      -> Profile(
                profileUiModel = uiState.profileUiModel, profileActions = ProfileActions(
                    onRefresh = profileScreenActions.onRefresh,
                    onAdd = profileScreenActions.onAdd,
                )
            )
            
            Error           -> TODO()
            InvalidResponse -> TODO()
            NoInternet      -> TODO()
            Timeout         -> TODO()
        }
    }
}

private class ProfileUiStateProvider : PreviewParameterProvider<ProfileUiState>{
    override val values: Sequence<ProfileUiState> = FAKE_UI_STATES
}

@ThemePreview
@Composable
private fun ProfileScreenPreview(@PreviewParameter(ProfileUiStateProvider::class) uiState: ProfileUiState) {
    CoffeeTheme {
        Surface(color = colorScheme.background) {
            ProfileScreenLayout(
                uiState = uiState,
                profileScreenActions = ProfileScreenActions(
                    onRefresh = {},
                    onAdd = {},
                ),
            )
        }
    }
}