package com.ephirium.coffee.data.compliment.model.entity

data class Compliment(
    val id: String,
    val text: String,
    val language: String,
    val authorId: String,
    val likedIds: List<String>,
)
