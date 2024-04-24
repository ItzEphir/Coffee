package com.ephirium.coffee.data.auth.model.request

import kotlinx.serialization.Serializable

@Serializable
internal data class SignInRequest(
    val login: String,
    val password: String,
)

@Serializable
internal data class SignUpRequest(
    val login: String,
    val name: String,
    val password: String,
)
