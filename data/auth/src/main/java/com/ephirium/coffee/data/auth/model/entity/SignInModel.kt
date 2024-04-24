package com.ephirium.coffee.data.auth.model.entity

data class SignInModel (
    val login: String,
    val password: String,
) : SigningModel