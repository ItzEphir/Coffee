package com.ephirium.coffee.databases.compliments.datastore

import com.ephirium.coffee.common.Status
import com.ephirium.coffee.databases.compliments.models.ComplimentDTO
import kotlinx.coroutines.flow.Flow

interface ComplimentsDataStore {
    suspend fun getCompliments(): Flow<Status<List<ComplimentDTO>>>
    
    suspend fun getComplimentById(id: String): Flow<Status<ComplimentDTO>>
    
    suspend fun createCompliment(compliment: ComplimentDTO): Flow<Status<Unit>>
    
    suspend fun updateCompliment(compliment: ComplimentDTO): Flow<Status<Unit>>
    
    suspend fun deleteCompliment(compliment: ComplimentDTO): Flow<Status<Unit>>
}