package com.ephirium.coffee.data.storage

import com.ephirium.coffee.data.datastore.ComplimentId
import com.ephirium.coffee.data.datastore.UserId
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

object Database {
    private val database by lazy { Firebase.firestore }
    
    val auth by lazy { Firebase.auth }
    
    val fcm by lazy { Firebase.messaging }
    
    val compliments by lazy { database.collection(CollectionsKeys.compliments) }
    
    val users by lazy { database.collection(CollectionsKeys.users) }
    
    val realm by lazy {
        Realm.open(
            configuration = RealmConfiguration.create(
                schema = setOf(
                    UserId::class,
                    ComplimentId::class,
                )
            )
        )
    }
    
    private object CollectionsKeys {
        const val compliments = "compliments"
        const val users = "users"
    }
}