package com.ephirium.coffee.domain.repository

import com.ephirium.coffee.domain.model.dto.ComplimentDTOBase
import kotlinx.coroutines.flow.Flow

interface ComplimentRepositoryBase {
    suspend fun getComplimentsFlow(): Flow<Result<List<ComplimentDTOBase>>>

    suspend fun getComplimentFlowById(id: String) : Flow<Result<ComplimentDTOBase>>
}