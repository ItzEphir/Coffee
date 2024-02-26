package com.ephirium.coffee.databases.users.models

import com.google.firebase.firestore.DocumentId

data class UserDTO(
    @DocumentId var id: String = "",
    var login: String = "",
    var email: String = "",
    var devices: List<String> = listOf(),
)
