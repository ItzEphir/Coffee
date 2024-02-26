package com.ephirium.coffee.domain.model.present

data class User(
    val id: String,
    val login: String,
    val email: String,
    val devices: List<String>,
)