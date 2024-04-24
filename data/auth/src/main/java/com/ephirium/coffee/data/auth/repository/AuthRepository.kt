package com.ephirium.coffee.data.auth.repository

import com.ephirium.coffee.common.ResponseResult
import com.ephirium.coffee.data.auth.model.dto.Token
import com.ephirium.coffee.data.auth.model.entity.SecretModel
import com.ephirium.coffee.data.auth.model.entity.SignInModel
import com.ephirium.coffee.data.auth.model.entity.SignUpModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun signIn(signInModel: SignInModel): Flow<ResponseResult<Token>>
    suspend fun signUp(signUpModel: SignUpModel): Flow<ResponseResult<Token>>
    suspend fun authorize(token: Token): Flow<ResponseResult<Unit>>
    suspend fun getSecret(token: Token): Flow<ResponseResult<SecretModel>>
}