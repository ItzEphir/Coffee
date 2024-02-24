package com.ephirium.coffee.databases.compliment_id.datastore

import com.ephirium.coffee.databases.compliment_id.models.ComplimentId
import kotlinx.coroutines.flow.Flow

interface ComplimentIdDataStore {
    
    suspend fun getComplimentId() : Flow<ComplimentId?>
    
    suspend fun setComplimentId(complimentId: ComplimentId): Flow<Unit>
}