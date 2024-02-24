package com.ephirium.coffee.databases.compliment_id.datastore

import com.ephirium.coffee.databases.compliment_id.database.ComplimentIdDatabase
import com.ephirium.coffee.databases.compliment_id.models.ComplimentId
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.asFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.mapLatest

internal class ComplimentIdDataStoreImpl : ComplimentIdDataStore {
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getComplimentId() =
        ComplimentIdDatabase.realm.query(ComplimentId::class).asFlow()
            .mapLatest { result -> result.list.lastOrNull() }
    
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun setComplimentId(complimentId: ComplimentId) =
        ComplimentIdDatabase.realm.write { copyToRealm(complimentId, UpdatePolicy.ALL) }.asFlow()
            .mapLatest { }
}