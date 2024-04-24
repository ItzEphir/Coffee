package com.ephirium.coffee.data.auth.config

data class RemoteConfig(val url: String, val port: String) {
    val connectUrl = "$url:$port"
}
