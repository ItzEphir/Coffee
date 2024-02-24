package com.ephirium.coffee.databases.compliments.di

import com.ephirium.coffee.databases.compliments.datastore.ComplimentsDataStore
import com.ephirium.coffee.databases.compliments.datastore.ComplimentsDataStoreImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val complimentsModule = module{
    singleOf(::ComplimentsDataStoreImpl) bind ComplimentsDataStore::class
}