package com.ephirium.coffee.data.auth.service

import com.ephirium.coffee.core.result.ResponseResult
import com.ephirium.coffee.data.auth.model.dto.Token
import com.ephirium.coffee.data.auth.model.request.SignInRequest
import com.ephirium.coffee.data.auth.model.request.SignUpRequest
import com.ephirium.coffee.data.auth.model.response.SecretResponse
import com.ephirium.coffee.data.auth.model.response.SignInResponse
import com.ephirium.coffee.data.auth.model.response.SignUpResponse

internal interface AuthService {
    suspend fun signIn(signInRequest: SignInRequest): ResponseResult<SignInResponse>
    suspend fun signUp(signUpRequest: SignUpRequest): ResponseResult<SignUpResponse>
    suspend fun authenticate(token: Token): ResponseResult<Unit>
    suspend fun getSecret(token: Token): ResponseResult<SecretResponse>
}