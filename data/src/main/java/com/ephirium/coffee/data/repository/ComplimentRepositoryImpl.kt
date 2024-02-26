package com.ephirium.coffee.data.repository

import com.ephirium.coffee.common.map
import com.ephirium.coffee.data.mappers.map
import com.ephirium.coffee.databases.compliments.datastore.ComplimentsDataStore
import com.ephirium.coffee.domain.repository.ComplimentRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.mapLatest

internal class ComplimentRepositoryImpl(
    private val complimentsDataStore: ComplimentsDataStore,
) : ComplimentRepository {
    
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getCompliments() =
        complimentsDataStore.getCompliments().mapLatest { status ->
            status.map { compliments -> compliments.map { it.map() } }
        }
    
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getComplimentById(id: String) =
        complimentsDataStore.getComplimentById(id).mapLatest { status ->
            status.map { compliment -> compliment.map() }
        }
}