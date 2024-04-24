package com.ephirium.coffee.data.auth.model.entity

data class SignUpModel(
    val login: String,
    val name: String,
    val password: String,
): SigningModel