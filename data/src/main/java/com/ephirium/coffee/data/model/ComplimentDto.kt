package com.ephirium.coffee.data.model

import com.ephirium.coffee.domain.model.ComplimentDtoBase

data class ComplimentDto(
    override var id: String = String(),
    override var text: String = String(),
    override var likes: Int = 0,
) : ComplimentDtoBase
