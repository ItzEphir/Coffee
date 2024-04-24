package com.ephirium.coffee.data.auth_token.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.ephirium.coffee.data.auth_token.model.Token
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

internal class TokenDataStoreRepository(
    private val dataStore: DataStore<Preferences>,
) : TokenRepository {
    override suspend fun getToken(): Token? = coroutineScope {
        async(Dispatchers.IO) {
            dataStore.data.firstOrNull()?.get(tokenKey).also{
                println(it)
            }
        }.await()
    }
    
    override suspend fun setToken(token: Token) {
        coroutineScope {
            launch(Dispatchers.IO) {
                dataStore.edit { preferences ->
                    preferences[tokenKey] = token
                }
            }
        }
    }
    
    companion object {
        private const val TOKEN_KEY = "token"
        private val tokenKey = stringPreferencesKey(TOKEN_KEY)
    }
}