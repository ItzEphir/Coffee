package com.ephirium.coffee.databases.compliment_id.database

import com.ephirium.coffee.databases.compliment_id.models.ComplimentId
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

internal object ComplimentIdDatabase {
    private val configuration by lazy {
        RealmConfiguration.create(
            schema = setOf(
                ComplimentId::class,
            )
        )
    }
    
    val realm by lazy {
        Realm.open(configuration = configuration)
    }
}