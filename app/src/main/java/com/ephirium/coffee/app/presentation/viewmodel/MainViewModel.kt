package com.ephirium.coffee.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.ephirium.coffee.app.preferences.PreferenceManager
import com.ephirium.coffee.app.presentation.state.MainScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class MainViewModel : ViewModel() {
    abstract val mainScreenState: StateFlow<MainScreenState>

    abstract var visibility: Boolean
    abstract var compliment: String
}

private class MainViewModelImpl(preferenceManager: PreferenceManager) : MainViewModel() {

    override val mainScreenState: MutableStateFlow<MainScreenState> = MutableStateFlow(
        MainScreenState(true, preferenceManager.compliment ?: "")
    )

    override var visibility: Boolean
        get() = mainScreenState.value.isVisible
        set(value) {
            mainScreenState.value = mainScreenState.value.copy(isVisible = value)
        }

    override var compliment: String
        get() = mainScreenState.value.compliment
        set(value) {
            mainScreenState.value = mainScreenState.value.copy(compliment = value)
        }
}

fun createMainViewModel(preferenceManager: PreferenceManager): MainViewModel{
    return MainViewModelImpl(preferenceManager)
}
