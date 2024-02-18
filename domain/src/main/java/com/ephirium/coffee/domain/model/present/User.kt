package com.ephirium.coffee.domain.model.present

data class User(val id: String, val user: String, val devices: List<String>)