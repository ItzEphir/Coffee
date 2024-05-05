package com.ephirium.coffee.feature.compliment_editor.di

import com.ephirium.coffee.feature.compliment_editor.presentation.viewmodel.ComplimentEditorScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val complimentEditorFeatureModule = module {
    viewModelOf(::ComplimentEditorScreenViewModel)
}