package com.ephirium.coffee.app.presentation

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.simonsickle.sharedpreferencedelegate.delegate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.random.Random
import kotlin.random.nextInt

abstract class ComplimentViewModel(application: Application) :
    AndroidViewModel(application = application) {
    abstract val compliment: StateFlow<String>
    protected abstract var currentIndex: Int

    abstract fun changeCompliment()

    var compliments: List<String> = listOf()
        protected set

    open fun loadCompliments(compliments: List<String>) {
        this.compliments = compliments
    }
}


internal class ComplimentViewModelImpl(private val application: Application) :
    ComplimentViewModel(application) {
    private val sharedPreferences = application.getSharedPreferences("pref", Context.MODE_PRIVATE)
    private var stringCompliment by sharedPreferences.delegate("compliment", String())
    override val compliment = MutableStateFlow(stringCompliment as String)

    override var currentIndex = 0

    override fun changeCompliment() {
        stringCompliment = compliments[getIndex(compliments.indices)]
        compliment.value = stringCompliment as String
    }

    override fun loadCompliments(compliments: List<String>) {
        super.loadCompliments(compliments)
        compliment.value = compliments[getIndex(compliments.indices)]
    }

    private fun getIndex(range: IntRange): Int = when (val index = Random.nextInt(range)) {
        currentIndex -> getIndex(range)
        else         -> {
            currentIndex = index
            index
        }
    }
}