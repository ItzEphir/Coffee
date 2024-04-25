package com.ephirium.coffee.data.compliment.model.request

import kotlinx.serialization.Serializable

@Serializable
data class UpdateComplimentRequest(
    val text: String,
    val language: String,
)

@Serializable
data class CreateComplimentRequest(
    val text: String,
    val language: String,
)