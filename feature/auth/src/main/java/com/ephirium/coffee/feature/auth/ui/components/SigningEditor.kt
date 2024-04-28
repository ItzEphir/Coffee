package com.ephirium.coffee.feature.auth.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.IntrinsicSize.Min
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ephirium.coffee.feature.auth.R
import com.ephirium.coffee.feature.auth.R.string
import com.ephirium.coffee.feature.auth.presentation.model.SigningUiModel
import com.ephirium.coffee.feature.auth.presentation.model.SigningUiModel.AuthorizeState
import com.ephirium.coffee.feature.auth.presentation.model.SigningUiModel.AuthorizeState.In
import com.ephirium.coffee.feature.auth.presentation.model.SigningUiModel.AuthorizeState.Up
import com.ephirium.coffee.feature.auth.presentation.state.AuthUiState.Signing
import com.ephirium.coffee.preview.ThemePreview
import com.ephirium.coffee.theme.CoffeeTheme

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
        Icon(
            painter = painterResource(id = R.drawable.coffee_logo),
            contentDescription = null,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            tint = MaterialTheme.colorScheme.primary,
        )

        Text(
            text = "Coffee",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.displayLarge,
            fontWeight = FontWeight.ExtraBold
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        (model.authorizeState as? Up)?.let { up ->
            TextField(
                value = up.name, onValueChange = onNameChanged,
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
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Button(
            onClick = {
                onPublish(model.authorizeState)
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
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
                }, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center
            )
        }
    }
}


@ThemePreview
@Composable
private fun SigningEditorPreview() {
    CoffeeTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            SigningEditor(
                uiState = Signing(
                    SigningUiModel(
                        login = "", password = "", Up("")
                    )
                ),
                onLoginChanged = {},
                onPasswordChanged = {},
                onNameChanged = {},
                onPublish = {},
                onGoToAnother = {},
            )
        }
    }
}