package com.ephirium.coffee.data.compliment.config

import com.ephirium.coffee.core.network.RemoteConfig

internal class RouteProvider(private val remoteConfig: RemoteConfig) {
    val complimentRoute = "${remoteConfig.connectUrl}${Routes.COMPLIMENT}"
    fun complimentRoute(id: String) = "$complimentRoute/$id"
    val randomComplimentRoute = "${remoteConfig.connectUrl}${Routes.COMPLIMENT_RANDOM}"
    fun like(id: String) = "${remoteConfig.connectUrl}${Routes.LIKE}/$id"
    val complimentsRoute = "${remoteConfig.connectUrl}${Routes.COMPLIMENTS}"
}