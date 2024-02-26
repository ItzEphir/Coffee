package com.ephirium.coffee.auth.di

import com.ephirium.coffee.auth.datastore.AuthDataStore
import com.ephirium.coffee.auth.datastore.AuthDataStoreImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val authModule = module {
    singleOf(::AuthDataStoreImpl) bind AuthDataStore::class
}