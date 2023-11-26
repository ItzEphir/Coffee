package com.ephirium.coffee.app.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ephirium.coffee.app.preferences.PreferenceManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.random.Random
import kotlin.random.nextInt

abstract class ComplimentViewModel(application: Application) :
    AndroidViewModel(application = application) {
    abstract val compliment: StateFlow<String>
    protected abstract var currentIndex: Int

    abstract fun changeCompliment()

    protected var compliments: List<String> = listOf()
        private set

    open fun loadCompliments(compliments: List<String>) {
        this.compliments = compliments
    }
}


private class ComplimentViewModelImpl(application: Application) : ComplimentViewModel(application) {
    private val preferenceManager = PreferenceManager(application)
    override val compliment = MutableStateFlow(preferenceManager.compliment ?: String())

    override var currentIndex = 0

    override fun changeCompliment() {
        preferenceManager.compliment = compliments[getIndex(compliments.indices)]
        compliment.value = preferenceManager.compliment as String
    }

    private fun getIndex(range: IntRange): Int = when (val index = Random.nextInt(range)) {
        currentIndex -> getIndex(range)
        else         -> {
            currentIndex = index
            index
        }
    }
}

internal fun createComplimentViewModel(application: Application): ComplimentViewModel =
    ComplimentViewModelImpl(application)
