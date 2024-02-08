package com.ephirium.coffee.data.model

import com.ephirium.coffee.domain.model.dto.UserDTOBase
import com.google.firebase.firestore.DocumentId

data class UserDTOImpl(
    @DocumentId override var id: String = "",
    override var user: String = "",
    override var token: String = "",
) : UserDTOBase