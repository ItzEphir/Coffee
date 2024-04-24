package com.ephirium.coffee.feature.auth.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ephirium.coffee.common.ResponseResult.Failure.*
import com.ephirium.coffee.common.ResponseResult.Failure.Error
import com.ephirium.coffee.common.on
import com.ephirium.coffee.common.onOk
import com.ephirium.coffee.data.auth.model.entity.SignInModel
import com.ephirium.coffee.data.auth.model.entity.SignUpModel
import com.ephirium.coffee.data.auth.repository.AuthRepository
import com.ephirium.coffee.data.auth_token.repository.TokenRepository
import com.ephirium.coffee.feature.auth.presentation.event.AuthUiEvent
import com.ephirium.coffee.feature.auth.presentation.event.AuthUiEvent.*
import com.ephirium.coffee.feature.auth.presentation.event.AuthUiEvent.Loading
import com.ephirium.coffee.feature.auth.presentation.mapper.SigningUiModelMapper.Companion.toModel
import com.ephirium.coffee.feature.auth.presentation.model.SigningUiModel
import com.ephirium.coffee.feature.auth.presentation.model.SigningUiModel.AuthorizeState.In
import com.ephirium.coffee.feature.auth.presentation.model.SigningUiModel.AuthorizeState.Up
import com.ephirium.coffee.feature.auth.presentation.state.AuthUiState
import com.ephirium.coffee.feature.auth.presentation.state.AuthUiState.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onSubscription
import kotlinx.coroutines.launch

internal class AuthScreenViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val tokenRepository: TokenRepository,
    private val authRepository: AuthRepository,
) : ViewModel() {
    
    val uiState = savedStateHandle.getStateFlow<AuthUiState>(UI_STATE_KEY, AuthUiState.Loading)
    
    private val event = MutableStateFlow<AuthUiEvent>(Loading)
    
    private var job: Job? = null
    
    private var supervisorJob: Job? = null
    
    init {
        onEvent()
    }
    
    fun passEvent(authUiEvent: AuthUiEvent) {
        viewModelScope.launch {
            event.emit(authUiEvent)
        }
    }
    
    private fun setUiState(uiState: AuthUiState) {
        savedStateHandle[UI_STATE_KEY] = uiState
    }
    
    private fun onEvent() {
        supervisorJob = viewModelScope.launch {
            event.collect { authUiEvent ->
                println(authUiEvent)
                when (authUiEvent) {
                    Loading            -> onLoading()
                    GoToSignIn         -> goToSignIn()
                    GoToSignUp         -> goToSignUp()
                    is LoginChanged    -> onLoginChanged(authUiEvent.login)
                    is NameChanged     -> onNameChanged(authUiEvent.name)
                    is PasswordChanged -> onPasswordChanged(authUiEvent.password)
                    SignIn             -> signIn()
                    SignUp             -> signUp()
                    Retry              -> onRetry()
                    CancelLoading      -> onCancelLoading()
                }
            }
        }
    }
    
    private fun onCancelLoading() = job?.cancel()
    
    private fun onRetry() = onLoading() // Can be something else
    
    private fun onLoading() {
        job = viewModelScope.launch {
            println("qqq")
            setUiState(AuthUiState.Loading)
            println("sss")
            val token = tokenRepository.getToken() ?: run {
                println("www")
                passEvent(GoToSignIn)
                return@launch
            }
            authRepository.authorize(token).collectLatest { responseResult ->
                responseResult.onOk {
                    setUiState(Authorized)
                }.on<NoInternetError> {
                    it.throwable.printStackTrace()
                    setUiState(NoInternet)
                }.on<TimeoutError> {
                    it.throwable.printStackTrace()
                    setUiState(Timeout)
                }.on<HttpResponseFailure> {
                    it.throwable.printStackTrace()
                    passEvent(GoToSignIn)
                }.on<Error> {
                    it.throwable.printStackTrace()
                    setUiState(AuthUiState.Error)
                }
            }
        }
    }
    
    private fun onPasswordChanged(password: String) {
        (uiState.value as Signing?)?.let { signing ->
            setUiState(signing.copy(signingUiModel = signing.signingUiModel.copy(password = password)))
        }
    }
    
    private fun onNameChanged(name: String) {
        (uiState.value as? Signing)?.let { signing ->
            (signing.signingUiModel.authorizeState as? Up)?.let { signingUp ->
                setUiState(
                    signing.copy(
                        signingUiModel = signing.signingUiModel.copy(
                            authorizeState = signingUp.copy(
                                name = name
                            )
                        )
                    )
                )
            }
        }
    }
    
    private fun onLoginChanged(login: String) {
        (uiState.value as? Signing)?.let { signing: Signing ->
            setUiState(signing.copy(signingUiModel = signing.signingUiModel.copy(login = login)))
        }
    }
    
    private fun goToSignUp() {
        setUiState(
            uiState = Signing(
                signingUiModel = SigningUiModel(
                    login = "", password = "", authorizeState = Up(
                        name = ""
                    )
                )
            )
        )
    }
    
    private fun goToSignIn() {
        setUiState(
            uiState = Signing(
                signingUiModel = SigningUiModel(
                    login = "", password = "", authorizeState = In
                )
            )
        )
    }
    
    private fun signUp() {
        (uiState.value as? Signing)?.let { signing ->
            (signing.signingUiModel.toModel() as? SignUpModel)?.let { signUpModel ->
                job = viewModelScope.launch {
                    setUiState(PartialLoading)
                    authRepository.signUp(signUpModel).collectLatest { responseResult ->
                        responseResult.onOk {
                            tokenRepository.setToken(it)
                            setUiState(Authorized)
                        }.on<NoInternetError> {
                            it.throwable.printStackTrace()
                            setUiState(NoInternet)
                        }.on<TimeoutError> {
                            it.throwable.printStackTrace()
                            setUiState(Timeout)
                        }.on<HttpResponseFailure> {
                            it.throwable.printStackTrace()
                            setUiState(AuthUiState.Error)
                        }.on<Error> {
                            it.throwable.printStackTrace()
                            setUiState(AuthUiState.Error)
                        }
                    }
                }
            }
        }
    }
    
    private fun signIn() {
        (uiState.value as? Signing)?.let { signing ->
            (signing.signingUiModel.toModel() as? SignInModel)?.let { signInModel ->
                job = viewModelScope.launch {
                    setUiState(PartialLoading)
                    authRepository.signIn(signInModel).collectLatest { responseResult ->
                        responseResult.onOk {
                            tokenRepository.setToken(it)
                            setUiState(Authorized)
                        }.on<NoInternetError> {
                            it.throwable.printStackTrace()
                            setUiState(NoInternet)
                        }.on<TimeoutError> {
                            it.throwable.printStackTrace()
                            setUiState(Timeout)
                        }.on<HttpResponseFailure> {
                            it.throwable.printStackTrace()
                            setUiState(AuthUiState.Error)
                        }.on<Error> {
                            it.throwable.printStackTrace()
                            setUiState(AuthUiState.Error)
                        }
                    }
                }
            }
        }
    }
    
    companion object {
        private const val UI_STATE_KEY = "auth_ui_state"
    }
}