package com.ephirium.coffee.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ephirium.coffee.app.preferences.PreferenceManager
import com.ephirium.coffee.app.presentation.state.MainScreenState
import com.ephirium.coffee.domain.usecase.GetComplimentByIdUseCase
import com.ephirium.coffee.domain.usecase.GetRandomComplimentUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class MainViewModel : ViewModel() {
    abstract val mainScreenState: StateFlow<MainScreenState>
    
    abstract fun changeState(state: MainScreenState)
    
    abstract suspend fun emitState(state: MainScreenState)
    
    abstract fun loadCompliment(
        onException: (exception: Throwable) -> Unit,
    )
    
    abstract fun changeCompliment(
        onException: (exception: Throwable) -> Unit,
    )
}

private class MainViewModelImpl(
    private val getComplimentByIdUseCase: GetComplimentByIdUseCase,
    private val getRandomComplimentUseCase: GetRandomComplimentUseCase,
    private val preferenceManager: PreferenceManager,
) : MainViewModel() {
    
    override val mainScreenState: MutableStateFlow<MainScreenState> = MutableStateFlow(
        MainScreenState(isVisible = true, compliment = null)
    )
    
    override fun changeState(state: MainScreenState) {
        mainScreenState.update {
            state
        }
    }
    
    override suspend fun emitState(state: MainScreenState) {
        mainScreenState.emit(state)
    }
    
    override fun loadCompliment(
        onException: (exception: Throwable) -> Unit,
    ) {
        viewModelScope.launch {
            if (mainScreenState.value.compliment == null) {
                when (val id = preferenceManager.complimentId) {
                    null -> getRandomComplimentUseCase.execute().catch { throwable ->
                        onException(throwable)
                    }.collectLatest { compliment ->
                        mainScreenState.collectLatest {
                            mainScreenState.emit(it.copy(compliment = compliment))
                        }
                    }
                    
                    else -> {
                        getComplimentByIdUseCase.execute(id).catch { throwable ->
                            onException(throwable)
                        }.collectLatest { compliment ->
                            mainScreenState.collectLatest {
                                mainScreenState.emit(it.copy(compliment = compliment))
                            }
                        }
                    }
                }
            }
        }
    }
    
    override fun changeCompliment(
        onException: (exception: Throwable) -> Unit,
    ) {
        viewModelScope.launch {
            getRandomComplimentUseCase.execute(
                when (val oldCompliment = mainScreenState.value.compliment) {
                    null -> null
                    else -> oldCompliment.id
                }
            ).catch {
                onException(it)
            }.collectLatest { compliment ->
                mainScreenState.collectLatest {
                    mainScreenState.emit(it.copy(compliment = compliment))
                }
            }
        }
    }
}

fun createMainViewModel(
    getComplimentByIdUseCase: GetComplimentByIdUseCase,
    getRandomComplimentUseCase: GetRandomComplimentUseCase,
    preferenceManager: PreferenceManager,
): MainViewModel {
    return MainViewModelImpl(
        getComplimentByIdUseCase, getRandomComplimentUseCase, preferenceManager
    )
}
