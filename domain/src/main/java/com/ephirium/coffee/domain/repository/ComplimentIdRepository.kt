package com.ephirium.coffee.domain.repository

import kotlinx.coroutines.flow.Flow

interface ComplimentIdRepository {
    
    suspend fun getSavedComplimentId() : Flow<String?>
    
    suspend fun saveComplimentId(id: String) : Flow<Unit>
}