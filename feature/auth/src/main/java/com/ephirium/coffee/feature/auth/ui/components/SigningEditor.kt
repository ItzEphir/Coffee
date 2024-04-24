package com.ephirium.coffee.feature.auth.ui.components

import android.os.Build.VERSION_CODES
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.IntrinsicSize.Min
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.ephirium.coffee.feature.auth.R.string
import com.ephirium.coffee.feature.auth.presentation.model.SigningUiModel
import com.ephirium.coffee.feature.auth.presentation.model.SigningUiModel.AuthorizeState
import com.ephirium.coffee.feature.auth.presentation.model.SigningUiModel.AuthorizeState.In
import com.ephirium.coffee.feature.auth.presentation.model.SigningUiModel.AuthorizeState.Up
import com.ephirium.coffee.feature.auth.presentation.state.AuthUiState.Signing

@Composable
internal fun SigningEditor(
    uiState: Signing,
    onLoginChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onNameChanged: (String) -> Unit,
    onPublish: (AuthorizeState) -> Unit,
    onGoToAnother: (AuthorizeState) -> Unit,
) {
    val model = uiState.signingUiModel
    Column(modifier = Modifier.height(Min), verticalArrangement = Arrangement.Center) {
        (model.authorizeState as? Up)?.let {
            TextField(
                value = it.name, onValueChange = onNameChanged,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(16.dp)),
                placeholder = {
                    Text(text = stringResource(string.name_placeholder))
                },
            )
        }
        
        TextField(
            value = model.login,
            onValueChange = onLoginChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp)),
            placeholder = {
                Text(text = stringResource(string.login_placeholder))
            },
        )
        
        TextField(
            value = model.password,
            onValueChange = onPasswordChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp)),
            placeholder = {
                Text(text = stringResource(string.password_placeholder))
            },
        )
        Button(
            onClick = {
                onPublish(model.authorizeState)
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 64.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(
                text = when (model.authorizeState) {
                    In    -> stringResource(string.sign_in_button)
                    is Up -> stringResource(string.sign_up_button)
                }
            )
        }
        
        TextButton(
            onClick = {
                onGoToAnother(model.authorizeState)
            },
            modifier = Modifier.align(Alignment.CenterHorizontally),
        ) {
            Text(
                text = when (model.authorizeState) {
                    In    -> stringResource(string.go_to_sign_up_button)
                    is Up -> stringResource(string.go_to_sign_in_button)
                }
            )
        }
    }
}

@RequiresApi(VERSION_CODES.S)
@PreviewLightDark
@Composable
private fun SigningEditorPreview() {
    val isDarkMode = isSystemInDarkTheme()
    val context = LocalContext.current
    val colorScheme =
        if (isDarkMode) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    MaterialTheme(colorScheme) {
        Surface {
            SigningEditor(uiState = Signing(
                SigningUiModel(
                    login = "", password = "", Up("")
                )
            ),
                onLoginChanged = {},
                onPasswordChanged = {},
                onNameChanged = {},
                onPublish = {},
                onGoToAnother = {})
        }
    }
}