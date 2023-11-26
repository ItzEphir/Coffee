package com.ephirium.coffee.app.di

import com.ephirium.coffee.app.presentation.createComplimentViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        createComplimentViewModel(androidApplication())
    }
}