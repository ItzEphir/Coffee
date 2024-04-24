package com.ephirium.coffee.data.auth.repository

import com.ephirium.coffee.common.ResponseResult
import com.ephirium.coffee.common.map
import com.ephirium.coffee.data.auth.mapper.AuthMapper.Companion.toModel
import com.ephirium.coffee.data.auth.mapper.AuthMapper.Companion.toRequest
import com.ephirium.coffee.data.auth.model.dto.Token
import com.ephirium.coffee.data.auth.model.entity.SecretModel
import com.ephirium.coffee.data.auth.model.entity.SignInModel
import com.ephirium.coffee.data.auth.model.entity.SignUpModel
import com.ephirium.coffee.data.auth.service.AuthService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

internal class RemoteAuthRepository(private val authService: AuthService) : AuthRepository {
    override suspend fun signIn(signInModel: SignInModel): Flow<ResponseResult<Token>> = flow {
        emit(authService.signIn(
            signInModel.toRequest()
        ).map { it.token })
    }.flowOn(Dispatchers.IO)
    
    override suspend fun signUp(signUpModel: SignUpModel): Flow<ResponseResult<Token>> = flow {
        emit(authService.signUp(signUpModel.toRequest()).map { it.token })
    }.flowOn(Dispatchers.IO)
    
    override suspend fun authorize(token: Token): Flow<ResponseResult<Unit>> = flow{
        emit(authService.authenticate(token))
    }.flowOn(Dispatchers.IO)
    
    override suspend fun getSecret(token: Token): Flow<ResponseResult<SecretModel>> = flow{
        emit(authService.getSecret(token).map { it.toModel() })
    }.flowOn(Dispatchers.IO)
}