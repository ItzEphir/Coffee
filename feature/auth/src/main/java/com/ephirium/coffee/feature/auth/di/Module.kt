package com.ephirium.coffee.feature.auth.di

import com.ephirium.coffee.data.auth.di.authDataModule
import com.ephirium.coffee.data.auth_token.di.authTokenDataModule
import com.ephirium.coffee.feature.auth.presentation.viewmodel.AuthScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val authFeatureModule = module {
    includes(authTokenDataModule, authDataModule)
    
    viewModelOf(::AuthScreenViewModel)
}