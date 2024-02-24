package com.ephirium.coffee.databases.compliments.config

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.time.Duration.Companion.seconds

internal val database by lazy { Firebase.firestore.collection("compliments") }

internal val timeout = 10.seconds