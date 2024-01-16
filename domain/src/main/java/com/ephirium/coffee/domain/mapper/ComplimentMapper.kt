package com.ephirium.coffee.domain.mapper

import com.ephirium.coffee.domain.model.dto.ComplimentDtoBase
import com.ephirium.coffee.domain.model.present.Compliment

fun ComplimentDtoBase.convert() = Compliment(id, text, likes)