package com.ephirium.coffee.domain.repository

import com.ephirium.coffee.domain.model.ComplimentDtoBase
import kotlinx.coroutines.flow.Flow

interface ComplimentRepositoryBase {
    suspend fun getCompliments(onException: (exception: Exception) -> Unit): Flow<ComplimentDtoBase>
}