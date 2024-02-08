package com.ephirium.coffee.domain.repository

import com.ephirium.coffee.domain.model.dto.ComplimentDTOBase
import kotlinx.coroutines.flow.Flow

interface ComplimentRepositoryBase {
    suspend fun getCompliments(): Flow<Result<List<ComplimentDTOBase>>>

    suspend fun getComplimentById(id: String) : Flow<Result<ComplimentDTOBase>>
}