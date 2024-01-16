package com.ephirium.coffee.data.model

import com.ephirium.coffee.domain.model.dto.ComplimentDtoBase

typealias ComplimentDto = ComplimentDtoImpl

data class ComplimentDtoImpl(
    override var id: String = String(),
    override var text: String = String(),
    override var likes: Int = 0,
) : ComplimentDtoBase
