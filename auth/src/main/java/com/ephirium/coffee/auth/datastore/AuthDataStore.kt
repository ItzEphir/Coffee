package com.ephirium.coffee.auth.datastore

import com.ephirium.coffee.common.Status
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AuthDataStore {
    
    suspend fun signIn(email: String, password: String): Flow<Status<FirebaseUser>>
    
    suspend fun signUp(email: String, password: String): Flow<Status<FirebaseUser>>
    
    suspend fun delete(user: FirebaseUser): Flow<Status<Unit>>
    
}