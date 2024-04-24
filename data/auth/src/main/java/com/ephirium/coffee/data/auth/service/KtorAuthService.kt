package com.ephirium.coffee.data.auth.service

import com.ephirium.coffee.common.ResponseResult
import com.ephirium.coffee.common.ResponseResult.Ok
import com.ephirium.coffee.common.ThrowableToResultMapper
import com.ephirium.coffee.data.auth.config.RouteProvider
import com.ephirium.coffee.data.auth.model.dto.Token
import com.ephirium.coffee.data.auth.model.request.SignInRequest
import com.ephirium.coffee.data.auth.model.request.SignUpRequest
import com.ephirium.coffee.data.auth.model.response.SecretResponse
import com.ephirium.coffee.data.auth.model.response.SignInResponse
import com.ephirium.coffee.data.auth.model.response.SignUpResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.timeout
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.ContentType.Companion
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import kotlin.time.Duration.Companion.seconds
import kotlin.time.DurationUnit.MILLISECONDS

internal class KtorAuthService(
    private val httpClient: HttpClient,
    private val routeProvider: RouteProvider,
) : AuthService {
    override suspend fun signIn(signInRequest: SignInRequest): ResponseResult<SignInResponse> =
        runCatching {
            Ok(httpClient.post(routeProvider.signInRoute) {
                contentType(ContentType.Application.Json)
                setBody(signInRequest)
                timeout {
                    requestTimeoutMillis = 10.seconds.toLong(MILLISECONDS)
                }
                println(url)
            }.also {
                println(it.bodyAsText())
            }.body<SignInResponse>())
        }.getOrElse {
            ThrowableToResultMapper.mapThrowable<SignInResponse>(it)
        }
    
    override suspend fun signUp(signUpRequest: SignUpRequest): ResponseResult<SignUpResponse> =
        runCatching {
            Ok(httpClient.post(routeProvider.signUpRoute) {
                contentType(ContentType.Application.Json)
                setBody(signUpRequest)
            }.also {
                println(it.bodyAsText())
            }.body<SignUpResponse>())
        }.getOrElse {
            ThrowableToResultMapper.mapThrowable<SignUpResponse>(it)
        }
    
    override suspend fun authenticate(token: Token): ResponseResult<Unit> = runCatching {
        httpClient.get(routeProvider.authenticateRoute) {
            headers[HttpHeaders.Authorization] = "Bearer $token"
        }
        Ok(Unit)
    }.getOrElse {
        ThrowableToResultMapper.mapThrowable<Unit>(it)
    }
    
    override suspend fun getSecret(token: Token): ResponseResult<SecretResponse> = runCatching {
        Ok(httpClient.get(routeProvider.secretRoute) {
            headers[HttpHeaders.Authorization] = "Bearer $token"
        }.body<SecretResponse>())
    }.getOrElse {
        ThrowableToResultMapper.mapThrowable<SecretResponse>(it)
    }
}