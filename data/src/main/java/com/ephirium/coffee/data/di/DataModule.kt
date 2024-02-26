package com.ephirium.coffee.data.di

import com.ephirium.coffee.auth.di.authModule
import com.ephirium.coffee.data.repository.AuthRepositoryImpl
import com.ephirium.coffee.data.repository.ComplimentIdRepositoryImpl
import com.ephirium.coffee.data.repository.ComplimentRepositoryImpl
import com.ephirium.coffee.data.repository.UserRepositoryImpl
import com.ephirium.coffee.databases.compliment_id.di.complimentIdModule
import com.ephirium.coffee.databases.compliments.di.complimentsModule
import com.ephirium.coffee.databases.users.di.usersModule
import com.ephirium.coffee.domain.repository.AuthRepository
import com.ephirium.coffee.domain.repository.ComplimentIdRepository
import com.ephirium.coffee.domain.repository.ComplimentRepository
import com.ephirium.coffee.domain.repository.UserRepository
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.ktx.messaging
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    
    includes(listOf(complimentIdModule, complimentsModule, usersModule, authModule))
    
    singleOf(::ComplimentRepositoryImpl) bind ComplimentRepository::class
    
    singleOf(::UserRepositoryImpl) bind UserRepository::class
    
    singleOf(::AuthRepositoryImpl) bind AuthRepository::class
    
    singleOf(::ComplimentIdRepositoryImpl) bind ComplimentIdRepository::class
    
    singleOf(Firebase::messaging) bind FirebaseMessaging::class
}