package com.ephirium.coffee.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.ephirium.coffee.app.preferences.PreferenceManager
import com.ephirium.coffee.app.presentation.state.MainScreenState
import com.ephirium.coffee.app.ui.components.MainScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

abstract class MainViewModel : ViewModel() {
    abstract val mainScreenState: StateFlow<MainScreenState>

    abstract fun changeState(state: MainScreenState)

    abstract suspend fun emitState(state: MainScreenState)

    abstract fun changeCompliment()
}

private class MainViewModelImpl(preferenceManager: PreferenceManager) : MainViewModel() {

    override val mainScreenState: MutableStateFlow<MainScreenState> = MutableStateFlow(
        MainScreenState(isVisible = true, compliment = null)
    )

    override fun changeState(state: MainScreenState) {
        mainScreenState.value = state
    }

    override suspend fun emitState(state: MainScreenState) {
        mainScreenState.emit(state)
    }

    override fun changeCompliment() {

    }
}

fun createMainViewModel(preferenceManager: PreferenceManager): MainViewModel {
    return MainViewModelImpl(preferenceManager)
}
