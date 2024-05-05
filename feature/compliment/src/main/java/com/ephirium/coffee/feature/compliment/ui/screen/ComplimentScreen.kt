package com.ephirium.coffee.feature.compliment.ui.screen

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ephirium.coffee.core.shimmer.Shimmer
import com.ephirium.coffee.core.shimmer.ShimmerColors
import com.ephirium.coffee.feature.compliment.R.string
import com.ephirium.coffee.feature.compliment.presentation.event.ComplimentUiEvent
import com.ephirium.coffee.feature.compliment.presentation.model.ComplimentUiModel
import com.ephirium.coffee.feature.compliment.presentation.state.ComplimentUiState
import com.ephirium.coffee.feature.compliment.presentation.state.ComplimentUiState.*
import com.ephirium.coffee.feature.compliment.presentation.viewmodel.ComplimentScreenViewModel
import com.ephirium.coffee.feature.compliment.ui.components.ComplimentCard
import com.ephirium.coffee.feature.compliment.ui.components.ErrorMessage
import com.ephirium.coffee.preview.ThemePreview
import com.ephirium.coffee.theme.CoffeeTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import kotlin.time.Duration.Companion.seconds

@Composable
fun ComplimentScreen() {
    val viewModel: ComplimentScreenViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    
    Scaffold { paddingValues ->
        ComplimentScreenLayout(
            uiState = uiState,
            complimentScreenActions = ComplimentScreenActions(
                onSwap = {
                    viewModel.passEvent(ComplimentUiEvent.Swap())
                },
                onRetry = {
                    viewModel.passEvent(ComplimentUiEvent.Retry())
                },
            ),
            modifier = Modifier.padding(paddingValues),
        )
    }
}

private data class ComplimentScreenActions(
    val onSwap: () -> Unit,
    val onRetry: () -> Unit,
)

@Composable
private fun ComplimentScreenLayout(
    uiState: ComplimentUiState,
    complimentScreenActions: ComplimentScreenActions,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        when (uiState) {
            Loading -> CircularProgressIndicator()
            is Compliment -> {
                var visible by remember {
                    mutableStateOf(true)
                }
                
                val coroutineScope = rememberCoroutineScope()
                AnimatedVisibility(
                    visible = visible,
                    enter = fadeIn() + slideIn { size ->
                        IntOffset(size.width / 4, size.height / 4)
                    },
                    exit = fadeOut() + slideOut { size ->
                        IntOffset(-size.width / 4, -size.height / 4)
                    },
                ) {
                    ComplimentCard(uiState.complimentModel)
                }
                
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                visible = false
                                delay(0.5.seconds)
                                complimentScreenActions.onSwap()
                                delay(0.5.seconds)
                                visible = true
                            }
                        },
                        modifier = Modifier.align(Alignment.Center),
                        colors = buttonColors(
                            containerColor = colorScheme.primaryContainer,
                            contentColor = colorScheme.primary,
                        ),
                        enabled = visible,
                    ) {
                        Text(
                            text = stringResource(string.refresh_button),
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
            
            AddClicked -> {
                Shimmer(
                    duration = 1.5.seconds,
                    modifier = Modifier.fillMaxSize(),
                    colors = ShimmerColors(
                        primary = colorScheme.primary,
                        secondary = colorScheme.inversePrimary,
                        tertiary = colorScheme.primaryContainer,
                    ),
                )
            }
            
            Error -> {
                ErrorMessage(
                    modifier = Modifier.align(Alignment.Center),
                    text = stringResource(string.something_went_wrong),
                    retryText = stringResource(string.retry),
                    onRetryClick = complimentScreenActions.onRetry,
                )
            }
            
            NoInternet -> {
                ErrorMessage(
                    modifier = Modifier.align(Alignment.Center),
                    text = stringResource(string.no_internet_connection),
                    retryText = stringResource(string.retry),
                    onRetryClick = complimentScreenActions.onRetry,
                )
            }
            
            Timeout -> {
                ErrorMessage(
                    modifier = Modifier.align(Alignment.Center),
                    text = stringResource(string.connection_timeout),
                    retryText = stringResource(string.retry),
                    onRetryClick = complimentScreenActions.onRetry,
                )
            }
        }
    }
}

private class UiStateProvider : PreviewParameterProvider<ComplimentUiState> {
    override val values: Sequence<ComplimentUiState> = sequenceOf(
        Loading,
        AddClicked,
        Error,
        NoInternet,
        Timeout,
        Compliment(ComplimentUiModel("You are so beautiful")),
    )
}

@ThemePreview
@Composable
private fun ComplimentScreenPreview(
    @PreviewParameter(
        UiStateProvider::class, Int.MAX_VALUE
    ) uiState: ComplimentUiState,
) {
    CoffeeTheme {
        Surface(color = colorScheme.background) {
            var previewUiState by remember {
                mutableStateOf(uiState)
            }
            ComplimentScreenLayout(
                uiState = previewUiState,
                complimentScreenActions = ComplimentScreenActions(
                    onSwap = {
                        previewUiState = Compliment(ComplimentUiModel("I love you"))
                    },
                    onRetry = {},
                ),
            )
        }
    }
}