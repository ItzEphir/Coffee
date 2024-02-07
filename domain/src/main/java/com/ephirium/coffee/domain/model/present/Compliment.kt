package com.ephirium.coffee.domain.model.present

import kotlinx.serialization.Serializable

@Serializable
data class Compliment(val id: String, val text: Map<String, String>)
