package com.ephirium.coffee.data.di

import com.ephirium.coffee.data.repository.AuthRepositoryImpl
import com.ephirium.coffee.data.repository.ComplimentRepositoryImpl
import com.ephirium.coffee.data.repository.UserRepositoryImpl
import com.ephirium.coffee.databases.compliment_id.di.complimentIdModule
import com.ephirium.coffee.databases.compliments.di.complimentsModule
import com.ephirium.coffee.databases.users.di.usersModule
import com.ephirium.coffee.domain.repository.AuthRepositoryBase
import com.ephirium.coffee.domain.repository.ComplimentRepository
import com.ephirium.coffee.domain.repository.UserRepositoryBase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    
    includes(listOf(complimentIdModule, complimentsModule, usersModule))
    
    singleOf(::ComplimentRepositoryImpl) bind ComplimentRepository::class
    
    singleOf(::UserRepositoryImpl) bind UserRepositoryBase::class
    
    singleOf(::AuthRepositoryImpl) bind AuthRepositoryBase::class
}