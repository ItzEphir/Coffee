package com.ephirium.coffee.feature.compliment_editor.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ephirium.coffee.core.shimmer.Shimmer
import com.ephirium.coffee.core.shimmer.ShimmerColors
import com.ephirium.coffee.feature.compliment_editor.presentation.event.ComplimentEditorUiEvent.*
import com.ephirium.coffee.feature.compliment_editor.presentation.model.ComplimentEditorUiModel
import com.ephirium.coffee.feature.compliment_editor.presentation.model.ComplimentUiModel
import com.ephirium.coffee.feature.compliment_editor.presentation.state.ComplimentEditorUiState
import com.ephirium.coffee.feature.compliment_editor.presentation.state.ComplimentEditorUiState.*
import com.ephirium.coffee.feature.compliment_editor.presentation.state.ComplimentEditorUiState.Loading
import com.ephirium.coffee.feature.compliment_editor.presentation.viewmodel.ComplimentEditorScreenViewModel
import com.ephirium.coffee.feature.compliment_editor.ui.components.ComplimentEditor
import com.ephirium.coffee.feature.compliment_editor.ui.components.ComplimentEditorActions
import com.ephirium.coffee.feature.compliment_editor.ui.components.ErrorMessage
import com.ephirium.coffee.preview.ThemePreview
import com.ephirium.coffee.theme.CoffeeTheme
import org.koin.androidx.compose.koinViewModel
import kotlin.time.Duration.Companion.seconds

@Composable
fun ComplimentEditorScreen(onPushed: suspend () -> Unit) {
    val viewModel: ComplimentEditorScreenViewModel = koinViewModel()
    val uiState by viewModel.complimentEditorUiState.collectAsStateWithLifecycle()
    
    LaunchedEffect(key1 = uiState) {
        if (uiState is Published) {
            onPushed()
        }
    }
    
    Scaffold { paddingValues ->
        ComplimentEditorScreenLayout(
            modifier = Modifier.padding(paddingValues),
            uiState = uiState,
            complimentEditorScreenActions = ComplimentEditorScreenActions(
                onTextChanged = {
                    viewModel.passEvent(TextChanged(it))
                },
                onLanguageCodeChanged = {
                    viewModel.passEvent(LanguageCodeChanged(it))
                },
                onPublish = {
                    viewModel.passEvent(Publish)
                },
                onRetry = {
                    viewModel.passEvent(Retry())
                },
            )
        )
    }
}

private data class ComplimentEditorScreenActions(
    val onTextChanged: (String) -> Unit,
    val onLanguageCodeChanged: (String) -> Unit,
    val onPublish: () -> Unit,
    val onRetry: () -> Unit,
)

@Composable
private fun ComplimentEditorScreenLayout(
    modifier: Modifier = Modifier,
    uiState: ComplimentEditorUiState,
    complimentEditorScreenActions: ComplimentEditorScreenActions,
) {
    Box(modifier = modifier.fillMaxSize()) {
        when (uiState) {
            Loading                  -> CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
            )
            
            is Editing               -> ComplimentEditor(
                modifier = Modifier.align(Alignment.Center),
                complimentEditorUiModel = uiState.complimentEditorUiModel,
                complimentEditorActions = ComplimentEditorActions(
                    onTextChanged = complimentEditorScreenActions.onTextChanged,
                    onLanguageCodeChanged = complimentEditorScreenActions.onLanguageCodeChanged,
                    onPublish = complimentEditorScreenActions.onPublish,
                ),
            )
            
            Publishing, is Published -> Shimmer(
                modifier = Modifier.fillMaxSize(),
                duration = 1.5.seconds,
                colors = ShimmerColors(
                    primary = colorScheme.primary,
                    secondary = colorScheme.inversePrimary,
                    tertiary = colorScheme.primaryContainer,
                ),
            )
            
            Timeout                  -> ErrorMessage(
                modifier = Modifier.align(Alignment.Center),
                text = "Connection timeout",
                retryText = "Retry",
                onRetryClick = complimentEditorScreenActions.onRetry,
            )
            
            NoInternet               -> ErrorMessage(
                modifier = Modifier.align(Alignment.Center),
                text = "No internet",
                retryText = "Retry",
                onRetryClick = complimentEditorScreenActions.onRetry,
            )
            
            Error                    -> ErrorMessage(
                modifier = Modifier.align(Alignment.Center),
                text = "Something went wrong...",
                retryText = "Retry",
                onRetryClick = complimentEditorScreenActions.onRetry,
            )
        }
    }
}

private class ComplimentEditorUiStateProvider : PreviewParameterProvider<ComplimentEditorUiState> {
    @Suppress("SpellCheckingInspection")
    override val values: Sequence<ComplimentEditorUiState> = sequenceOf(
        Loading,
        Editing(),
        Editing(ComplimentEditorUiModel(text = "You are so beautiful", languageCode = "en")),
        Editing(ComplimentEditorUiModel(text = "Ты такая красивая", languageCode = "ru")),
        Publishing,
        Published(ComplimentUiModel.default),
        Timeout,
        NoInternet,
        Error,
    )
}

@ThemePreview
@Composable
private fun ComplimentEditorScreenPreview(
    @PreviewParameter(
        ComplimentEditorUiStateProvider::class,
        Int.MAX_VALUE,
    ) uiState: ComplimentEditorUiState,
) {
    CoffeeTheme {
        Surface(color = colorScheme.background) {
            ComplimentEditorScreenLayout(
                uiState = uiState, complimentEditorScreenActions = ComplimentEditorScreenActions(
                    onTextChanged = {},
                    onLanguageCodeChanged = {},
                    onPublish = {},
                    onRetry = {},
                )
            )
        }
    }
}