package com.ephirium.coffee.domain.repository

import com.ephirium.coffee.common.Status
import com.ephirium.coffee.domain.model.present.Compliment
import kotlinx.coroutines.flow.Flow

interface ComplimentRepository {
    
    suspend fun getCompliments(): Flow<Status<List<Compliment>>>

    suspend fun getComplimentById(id: String) : Flow<Status<Compliment>>
}