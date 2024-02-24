package com.ephirium.coffee.databases.compliments.models

import com.google.firebase.firestore.DocumentId

data class ComplimentDTO(
    @DocumentId var id: String = "",
    var text: Map<String, String> = mapOf(),
    var user: String = "",
)
