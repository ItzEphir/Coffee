package com.ephirium.coffee.domain.repository

import com.ephirium.coffee.domain.model.dto.ComplimentDtoBase
import kotlinx.coroutines.flow.Flow

interface ComplimentRepositoryBase {
    suspend fun getComplimentsFlow(): Flow<Result<ComplimentDtoBase>>

    suspend fun getComplimentFlowById(id: String) : Flow<Result<ComplimentDtoBase>>
}