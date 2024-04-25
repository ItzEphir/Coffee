package com.ephirium.coffee.data.auth.service

import com.ephirium.coffee.core.result.ResponseResult
import com.ephirium.coffee.core.result.ResponseResult.Ok
import com.ephirium.coffee.core.result.ThrowableToResultMapper
import com.ephirium.coffee.data.auth.config.RouteProvider
import com.ephirium.coffee.data.auth.model.dto.Token
import com.ephirium.coffee.data.auth.model.request.SignInRequest
import com.ephirium.coffee.data.auth.model.request.SignUpRequest
import com.ephirium.coffee.data.auth.model.response.SecretResponse
import com.ephirium.coffee.data.auth.model.response.SignInResponse
import com.ephirium.coffee.data.auth.model.response.SignUpResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

internal class KtorAuthService(
    private val httpClient: HttpClient,
    private val routeProvider: RouteProvider,
) : AuthService {
    override suspend fun signIn(signInRequest: SignInRequest): ResponseResult<SignInResponse> =
        runCatching {
            Ok(httpClient.post(routeProvider.signInRoute) {
                contentType(ContentType.Application.Json)
                setBody(signInRequest)
            }.body<SignInResponse>())
        }.getOrElse(ThrowableToResultMapper::mapThrowable)
    
    override suspend fun signUp(signUpRequest: SignUpRequest): ResponseResult<SignUpResponse> =
        runCatching {
            Ok(httpClient.post(routeProvider.signUpRoute) {
                contentType(ContentType.Application.Json)
                setBody(signUpRequest)
            }.body<SignUpResponse>())
        }.getOrElse(ThrowableToResultMapper::mapThrowable)
    
    override suspend fun authenticate(token: Token): ResponseResult<Unit> = runCatching {
        httpClient.get(routeProvider.authenticateRoute) {
            bearerAuth(token)
        }
        Ok(Unit)
    }.getOrElse(ThrowableToResultMapper::mapThrowable)
    
    override suspend fun getSecret(token: Token): ResponseResult<SecretResponse> = runCatching {
        Ok(httpClient.get(routeProvider.secretRoute) {
            bearerAuth(token)
        }.body<SecretResponse>())
    }.getOrElse(ThrowableToResultMapper::mapThrowable)
}