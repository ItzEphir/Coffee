package com.ephirium.coffee.databases.users.models

import com.google.firebase.firestore.DocumentId

data class UserDTO(
    @DocumentId var id: String = "",
    var devices: List<String> = listOf(),
    var user: String = ""
)
