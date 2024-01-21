package com.ephirium.coffee.domain.mapper

import com.ephirium.coffee.domain.model.dto.ComplimentDTOBase
import com.ephirium.coffee.domain.model.present.Compliment

fun ComplimentDTOBase.convertForPresentation() = Compliment(id, text)