package com.ephirium.coffee.feature.auth.ui.screen

import android.os.Build.VERSION_CODES
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ephirium.coffee.feature.auth.R.string
import com.ephirium.coffee.feature.auth.presentation.event.AuthUiEvent.*
import com.ephirium.coffee.feature.auth.presentation.model.SigningUiModel
import com.ephirium.coffee.feature.auth.presentation.model.SigningUiModel.AuthorizeState.In
import com.ephirium.coffee.feature.auth.presentation.model.SigningUiModel.AuthorizeState.Up
import com.ephirium.coffee.feature.auth.presentation.state.AuthUiState
import com.ephirium.coffee.feature.auth.presentation.state.AuthUiState.*
import com.ephirium.coffee.feature.auth.presentation.state.AuthUiState.Loading
import com.ephirium.coffee.feature.auth.presentation.viewmodel.AuthScreenViewModel
import com.ephirium.coffee.feature.auth.ui.components.SigningEditor
import org.koin.androidx.compose.koinViewModel


@Composable
fun AuthScreen(onAuthorized: () -> Unit) {
    val viewModel: AuthScreenViewModel = koinViewModel()
    
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    
    LaunchedEffect(key1 = uiState) {
        if (uiState is Authorized) {
            onAuthorized()
        }
    }
    
    val actions = AuthScreenActions(
        onLoginChanged = { viewModel.passEvent(LoginChanged(it)) },
        onPasswordChanged = { viewModel.passEvent(PasswordChanged(it)) },
        onNameChanged = { viewModel.passEvent(NameChanged(it)) },
        onSignIn = { viewModel.passEvent(SignIn) },
        onSignUp = { viewModel.passEvent(SignUp) },
        onGoToSignIn = { viewModel.passEvent(GoToSignIn) },
        onGoToSignUp = { viewModel.passEvent(GoToSignUp) },
        onRetry = { viewModel.passEvent(Retry) },
        onDismiss = { viewModel.passEvent(CancelLoading) },
    )
    
    
    AuthScreenLayout(
        uiState = uiState,
        authScreenActions = actions,
    )
}

private data class AuthScreenActions(
    val onLoginChanged: (String) -> Unit,
    val onPasswordChanged: (String) -> Unit,
    val onNameChanged: (String) -> Unit,
    val onSignIn: () -> Unit,
    val onSignUp: () -> Unit,
    val onGoToSignIn: () -> Unit,
    val onGoToSignUp: () -> Unit,
    val onDismiss: () -> Unit,
    val onRetry: () -> Unit,
)

@Composable
private fun AuthScreenLayout(
    uiState: AuthUiState,
    authScreenActions: AuthScreenActions,
) {
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            when (uiState) {
                Authorized     -> Unit
                Error          -> {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = stringResource(string.something_went_wrong),
                            style = MaterialTheme.typography.headlineSmall,
                        )
                        Button(onClick = authScreenActions.onRetry) {
                            Text(text = stringResource(string.retry_button_text))
                        }
                    }
                }
                
                Loading        -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                
                NoInternet     -> {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = stringResource(string.no_internet_connection),
                            style = MaterialTheme.typography.headlineSmall,
                        )
                        Button(onClick = authScreenActions.onRetry) {
                            Text(text = stringResource(id = string.retry_button_text))
                        }
                    }
                }
                
                PartialLoading -> {
                    var openDialog by remember {
                        mutableStateOf(true)
                    }
                    if (openDialog) {
                        Dialog(
                            onDismissRequest = {
                                authScreenActions.onDismiss()
                                openDialog = false
                            },
                            properties = DialogProperties(
                                usePlatformDefaultWidth = false
                            ),
                        ) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                            }
                        }
                    }
                }
                
                is Signing     -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        SigningEditor(
                            uiState = uiState,
                            onLoginChanged = authScreenActions.onLoginChanged,
                            onPasswordChanged = authScreenActions.onPasswordChanged,
                            onNameChanged = authScreenActions.onNameChanged,
                            onPublish = {
                                when (it) {
                                    In    -> authScreenActions.onSignIn()
                                    is Up -> authScreenActions.onSignUp()
                                }
                            },
                            onGoToAnother = {
                                when (it) {
                                    In    -> authScreenActions.onGoToSignUp()
                                    is Up -> authScreenActions.onGoToSignIn()
                                }
                            },
                        )
                    }
                }
                
                Timeout        -> {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = stringResource(string.timeout),
                            style = MaterialTheme.typography.headlineSmall,
                        )
                        Button(onClick = authScreenActions.onRetry) {
                            Text(text = stringResource(id = string.retry_button_text))
                        }
                    }
                }
            }
        }
    }
}

private class SampleUserProvider : PreviewParameterProvider<AuthUiState> {
    override val values = sequenceOf(
        Loading,
        Error,
        PartialLoading,
        Timeout,
        NoInternet,
        Signing(SigningUiModel("", "", In)),
        Signing(SigningUiModel("", "", Up(""))),
    )
}

@RequiresApi(VERSION_CODES.S)
@PreviewLightDark
@Composable
private fun AuthScreenLayoutPreview(
    @PreviewParameter(SampleUserProvider::class, Int.MAX_VALUE) authUiState: AuthUiState,
) {
    val isDarkMode = isSystemInDarkTheme()
    val context = LocalContext.current
    val colorScheme =
        if (isDarkMode) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    MaterialTheme(colorScheme) {
        Surface(color = colorScheme.surface) {
            AuthScreenLayout(authUiState, AuthScreenActions({}, {}, {}, {}, {}, {}, {}, {}, {}))
        }
    }
}