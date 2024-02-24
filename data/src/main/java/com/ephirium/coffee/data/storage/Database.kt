package com.ephirium.coffee.data.storage

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging

object Database {
    private val database by lazy { Firebase.firestore }
    
    val auth by lazy { Firebase.auth }
    
    val fcm by lazy { Firebase.messaging }
    
    val users by lazy { database.collection(CollectionsKeys.users) }
    
    private object CollectionsKeys {
        const val users = "users"
    }
}