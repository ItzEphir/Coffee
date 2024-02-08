package com.ephirium.coffee.data.storage

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object Database {
    private val database by lazy { Firebase.firestore }

    val compliments by lazy {
        database.collection(CollectionsKeys.compliments)
    }

    val users by lazy{
        database.collection(CollectionsKeys.users)
    }
    
    private object CollectionsKeys {
        const val compliments = "compliments"
        const val users = "users"
    }
}