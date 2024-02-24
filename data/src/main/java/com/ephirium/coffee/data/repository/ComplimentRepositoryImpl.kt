package com.ephirium.coffee.data.repository

import com.ephirium.coffee.common.Status.*
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
            when (status) {
                is Success      -> Success(status.result.map { it.map() })
                is TimeoutError -> TimeoutError
                is NetworkError -> NetworkError
                is Error        -> Error
            }
        }
    
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getComplimentById(id: String) =
        complimentsDataStore.getComplimentById(id).mapLatest { status ->
            when (status) {
                is Success      -> Success(status.result.map())
                is TimeoutError -> TimeoutError
                is NetworkError -> NetworkError
                is Error        -> Error
            }
        }
}