package com.ephirium.coffee.data.repository

import com.ephirium.coffee.databases.compliment_id.datastore.ComplimentIdDataStore
import com.ephirium.coffee.databases.compliment_id.models.ComplimentId
import com.ephirium.coffee.domain.repository.ComplimentIdRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest

internal class ComplimentIdRepositoryImpl(private val complimentIdDataStore: ComplimentIdDataStore) :
    ComplimentIdRepository {
    
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getSavedComplimentId(): Flow<String?> =
        complimentIdDataStore.getComplimentId().mapLatest {
            it?.complimentId
        }
    
    override suspend fun saveComplimentId(id: String) =
        complimentIdDataStore.setComplimentId(ComplimentId().apply {
            complimentId = id
        })
}