package com.ephirium.coffee.data.compliment.di

import com.ephirium.coffee.data.compliment.config.RouteProvider
import com.ephirium.coffee.data.compliment.repository.ComplimentRepository
import com.ephirium.coffee.data.compliment.repository.RemoteComplimentRepository
import com.ephirium.coffee.data.compliment.service.ComplimentService
import com.ephirium.coffee.data.compliment.service.KtorComplimentService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val complimentDataModule = module {
    singleOf(::KtorComplimentService) bind ComplimentService::class
    singleOf(::RemoteComplimentRepository) bind ComplimentRepository::class
    singleOf(::RouteProvider) bind RouteProvider::class
}