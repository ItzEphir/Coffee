package com.ephirium.coffee.data.auth.config

import com.ephirium.coffee.core.network.RemoteConfig

internal class RouteProvider(remoteConfig: RemoteConfig) {
    val signInRoute = remoteConfig.connectUrl + Routes.SIGN_IN
    val signUpRoute = remoteConfig.connectUrl + Routes.SIGN_UP
    val authenticateRoute = remoteConfig.connectUrl + Routes.AUTHENTICATE
    val secretRoute = remoteConfig.connectUrl + Routes.SECRET
}