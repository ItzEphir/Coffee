package com.ephirium.coffee.feature.compliment.di

import com.ephirium.coffee.data.compliment.di.complimentDataModule
import com.ephirium.coffee.feature.compliment.presentation.viewmodel.ComplimentScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val complimentFeatureModule = module {
    includes(complimentDataModule)
    
    viewModelOf(::ComplimentScreenViewModel)
}