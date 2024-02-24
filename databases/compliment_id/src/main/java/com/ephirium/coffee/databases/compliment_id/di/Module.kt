package com.ephirium.coffee.databases.compliment_id.di

import com.ephirium.coffee.databases.compliment_id.datastore.ComplimentIdDataStore
import com.ephirium.coffee.databases.compliment_id.datastore.ComplimentIdDataStoreImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val complimentIdModule = module {
    singleOf(::ComplimentIdDataStoreImpl) bind ComplimentIdDataStore::class
}