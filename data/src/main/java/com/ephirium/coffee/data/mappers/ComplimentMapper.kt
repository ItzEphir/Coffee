package com.ephirium.coffee.data.mappers

import com.ephirium.coffee.databases.compliments.models.ComplimentDTO
import com.ephirium.coffee.domain.model.present.Compliment

fun ComplimentDTO.map(): Compliment = Compliment(id = id, text = text)