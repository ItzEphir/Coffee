package com.ephirium.coffee.data.auth.model.response

import com.ephirium.coffee.data.auth.model.dto.Token
import kotlinx.serialization.Serializable

@Serializable
internal data class SignInResponse(
    val token: Token,
)

@Serializable
internal data class SignUpResponse(
    val token: Token,
)

@Serializable
internal data class SecretResponse(
    val login: String,
    val name: String,
    val agent: String,
    val timestamp: String,
)