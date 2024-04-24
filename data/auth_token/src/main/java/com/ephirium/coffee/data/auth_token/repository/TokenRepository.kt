package com.ephirium.coffee.data.auth_token.repository

import com.ephirium.coffee.data.auth_token.model.Token

interface TokenRepository {
    suspend fun getToken(): Token?
    
    suspend fun setToken(token: Token)
}