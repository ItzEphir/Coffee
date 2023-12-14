package com.ephirium.coffee.domain.repository

import com.ephirium.coffee.domain.model.ComplimentDtoBase

interface ComplimentRepositoryBase {
    fun getCompliments(): List<ComplimentDtoBase>
}