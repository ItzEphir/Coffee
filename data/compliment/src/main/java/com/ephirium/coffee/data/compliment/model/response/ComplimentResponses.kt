package com.ephirium.coffee.data.compliment.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetComplimentResponse(
    val id: String,
    val text: String,
    val language: String,
    @SerialName("author") val authorId: String,
    @SerialName("liked") val likedIds: List<String>,
)

@Serializable
data class CreateComplimentResponse(
    val id: String,
    val text: String,
    val language: String,
    @SerialName("author") val authorId: String,
    @SerialName("liked") val likedIds: List<String>,
)

@Serializable
data class GetComplimentsResponse(
    val offset: Int,
    val limit: Int,
    @SerialName("author") val authorId: String,
    @SerialName("is_end") val isEnd: Boolean,
    val results: List<GetComplimentResponse>,
)