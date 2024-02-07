package com.ephirium.coffee.data.model

import com.ephirium.coffee.domain.model.dto.ComplimentDTOBase
import com.google.firebase.firestore.DocumentId

typealias ComplimentDTO = ComplimentDTOImpl

data class ComplimentDTOImpl(
    @DocumentId override var id: String = String(),
    override var text: Map<String, String> = mapOf(),
    override var user: String = String(),
) : ComplimentDTOBase
