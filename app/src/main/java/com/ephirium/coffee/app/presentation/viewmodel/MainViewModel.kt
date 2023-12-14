package com.ephirium.coffee.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.ephirium.coffee.app.preferences.PreferenceManager
import com.ephirium.coffee.app.presentation.state.MainScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class MainViewModel : ViewModel() {
    abstract val mainScreenState: StateFlow<MainScreenState>

    abstract fun changeState(state: MainScreenState)

    abstract suspend fun emitState(state: MainScreenState)
}

private class MainViewModelImpl(preferenceManager: PreferenceManager) : MainViewModel() {

    override val mainScreenState: MutableStateFlow<MainScreenState> = MutableStateFlow(
        MainScreenState(isVisible = true, compliment = preferenceManager.compliment)
    )

    override fun changeState(state: MainScreenState) {
        mainScreenState.value = state
    }

    override suspend fun emitState(state: MainScreenState) {
        mainScreenState.emit(state)
    }
}

fun createMainViewModel(preferenceManager: PreferenceManager): MainViewModel {
    return MainViewModelImpl(preferenceManager)
}
