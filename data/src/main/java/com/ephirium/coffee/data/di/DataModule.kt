package com.ephirium.coffee.data.di

import com.ephirium.coffee.data.repository.ComplimentRepositoryImpl
import com.ephirium.coffee.data.repository.UserRepositoryImpl
import com.ephirium.coffee.domain.repository.ComplimentRepositoryBase
import com.ephirium.coffee.domain.repository.UserRepositoryBase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {
    singleOf<ComplimentRepositoryBase>(::ComplimentRepositoryImpl)
    
    singleOf<UserRepositoryBase>(::UserRepositoryImpl)
}