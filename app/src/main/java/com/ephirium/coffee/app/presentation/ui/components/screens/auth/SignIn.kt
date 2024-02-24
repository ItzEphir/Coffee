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
fun SignIn(
    modifier: Modifier = Modifier,
    login: String = "",
    password: String = "",
    onLoginChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignIn: () -> Unit,
    onGoToSignUp: () -> Unit,
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
                value = login,
                onValueChange = onLoginChange,
            )
            
            TextField(
                modifier = Modifier.padding(textFieldPadding),
                value = password,
                onValueChange = onPasswordChange,
            )
            
            Button(modifier = Modifier.padding(top = 4.dp), onClick = onSignIn) {
                Text(text = stringResource(string.sign_in))
            }
            
            TextButton(onClick = onGoToSignUp) {
                Text(text = stringResource(string.go_to_sign_up))
            }
        }
    }
    
}

@Composable
@PreviewDynamicColors
@PreviewLightDark
internal fun SignInPreview() {
    CoffeeTheme {
        SignIn(
            login = "",
            password = "",
            onLoginChange = {},
            onPasswordChange = {},
            onSignIn = {},
            onGoToSignUp = {},
        )
    }
}