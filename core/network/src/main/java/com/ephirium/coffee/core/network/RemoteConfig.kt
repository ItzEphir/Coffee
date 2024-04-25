package com.ephirium.coffee.core.network

data class RemoteConfig(val url: String, val port: String) {
    val connectUrl = "$url:$port"
}
