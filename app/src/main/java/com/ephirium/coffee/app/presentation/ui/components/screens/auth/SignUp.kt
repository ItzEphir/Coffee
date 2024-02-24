package com.ephirium.coffee.app.presentation.ui.components.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.ephirium.coffee.app.R.string
import com.ephirium.coffee.app.presentation.ui.theme.CoffeeTheme

@Composable
fun SignUp(
    modifier: Modifier = Modifier,
    email: String,
    login: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onLoginChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignUp: () -> Unit,
    onGoToSignIn: () -> Unit,
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val textFieldPadding = PaddingValues(12.dp)
            
            TextField(
                modifier = Modifier.padding(textFieldPadding),
                value = email,
                onValueChange = onEmailChange,
            )
            
            TextField(
                modifier = Modifier.padding(textFieldPadding),
                value = login,
                onValueChange = onLoginChange,
            )
            
            TextField(
                modifier = Modifier.padding(textFieldPadding),
                value = password,
                onValueChange = onPasswordChange,
            )
            
            Button(modifier = Modifier.padding(top = 4.dp), onClick = onSignUp) {
                Text(text = stringResource(string.sign_up))
            }
            
            TextButton(onClick = onGoToSignIn) {
                Text(text = stringResource(string.go_to_sign_in))
            }
        }
    }
}

@PreviewDynamicColors
@PreviewLightDark
@Composable
internal fun SignUpPreview() {
    CoffeeTheme {
        SignUp(
            email = "",
            login = "",
            password = "",
            onEmailChange = {},
            onLoginChange = {},
            onPasswordChange = {},
            onSignUp = {},
            onGoToSignIn = {},
        )
    }
}