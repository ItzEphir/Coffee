package com.ephirium.coffee.feature.profile.di

import com.ephirium.coffee.data.compliment.di.complimentDataModule
import com.ephirium.coffee.feature.profile.presentation.viewmodel.ProfileScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val profileFeatureModule = module {
    includes(complimentDataModule)
    
    viewModelOf(::ProfileScreenViewModel)
}