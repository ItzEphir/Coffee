package com.ephirium.coffee.data.auth.model.entity

import kotlinx.datetime.Instant

data class SecretModel(
    val login: String,
    val name: String,
    val agent: String,
    val timestamp: Instant,
)
