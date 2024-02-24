package com.ephirium.coffee.databases.users.di

import com.ephirium.coffee.databases.users.datastore.UserDataStore
import com.ephirium.coffee.databases.users.datastore.UserDataStoreImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val usersModule = module {
    singleOf(::UserDataStoreImpl) bind UserDataStore::class
}