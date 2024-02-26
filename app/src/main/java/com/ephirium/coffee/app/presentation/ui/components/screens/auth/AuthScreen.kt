package com.ephirium.coffee.app.presentation.ui.components.screens.auth

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.ephirium.coffee.app.presentation.state.AuthScreenState.SignIn
import com.ephirium.coffee.app.presentation.state.AuthScreenState.SignUp
import com.ephirium.coffee.app.presentation.ui.navigation.Screens
import com.ephirium.coffee.app.presentation.viewmodel.AuthScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthScreen(navController: NavController, viewModel: AuthScreenViewModel = koinViewModel()) {
    
    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()
        when (uiState.value) {
            is SignIn -> SignIn(
                modifier = Modifier.padding(paddingValues),
                login = uiState.value.login,
                password = uiState.value.password,
                onLoginChange = viewModel::changeLogin,
                onPasswordChange = viewModel::changePassword,
                onSignIn = { // TODO: viewModel.signIn(...)
                    navController.navigate(Screens.COMPLIMENT.route)
                },
                onGoToSignUp = viewModel::goToSignUp,
            )
            
            is SignUp -> SignUp(
                modifier = Modifier.padding(paddingValues),
                email = (uiState.value as SignUp).email,
                login = uiState.value.login,
                password = uiState.value.password,
                onEmailChange = viewModel::changeEmail,
                onLoginChange = viewModel::changeLogin,
                onPasswordChange = viewModel::changePassword,
                onSignUp = { // TODO: viewModel.signUp(...)
                    navController.navigate(Screens.COMPLIMENT.route)
                },
                onGoToSignIn = viewModel::goToSignIn,
            )
        }
    }
}