package com.ephirium.coffee.data.compliment.service

import com.ephirium.coffee.core.result.ResponseResult
import com.ephirium.coffee.core.result.ResponseResult.Ok
import com.ephirium.coffee.core.result.ThrowableToResultMapper
import com.ephirium.coffee.data.compliment.config.RouteProvider
import com.ephirium.coffee.data.compliment.model.dto.Token
import com.ephirium.coffee.data.compliment.model.request.CreateComplimentRequest
import com.ephirium.coffee.data.compliment.model.request.UpdateComplimentRequest
import com.ephirium.coffee.data.compliment.model.response.CreateComplimentResponse
import com.ephirium.coffee.data.compliment.model.response.GetComplimentResponse
import com.ephirium.coffee.data.compliment.model.response.GetComplimentsResponse
import com.ephirium.coffee.data.compliment.service.KtorComplimentService.ComplimentsParameters.AUTHOR
import com.ephirium.coffee.data.compliment.service.KtorComplimentService.ComplimentsParameters.LIMIT
import com.ephirium.coffee.data.compliment.service.KtorComplimentService.ComplimentsParameters.OFFSET
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.*
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.datetime.Clock.System
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.koin.core.parameter.parametersOf

internal class KtorComplimentService(
    private val httpClient: HttpClient,
    private val routeProvider: RouteProvider,
) : ComplimentService {
    override suspend fun getCompliment(id: String): ResponseResult<GetComplimentResponse> =
        runCatching {
            Ok(httpClient.get(routeProvider.complimentRoute(id)).body<GetComplimentResponse>())
        }.getOrElse(ThrowableToResultMapper::mapThrowable)
    
    override suspend fun updateCompliment(
        id: String,
        authToken: Token,
        updateComplimentRequest: UpdateComplimentRequest,
    ): ResponseResult<Unit> = runCatching {
        Ok(httpClient.patch(routeProvider.complimentRoute(id)) {
            bearerAuth(authToken)
            contentType(ContentType.Application.Json)
            setBody(updateComplimentRequest)
        }.body<Unit>())
    }.getOrElse(ThrowableToResultMapper::mapThrowable)
    
    override suspend fun createCompliment(
        authToken: Token,
        createComplimentRequest: CreateComplimentRequest,
    ): ResponseResult<CreateComplimentResponse> = runCatching {
        Ok(httpClient.post(routeProvider.complimentRoute) {
            bearerAuth(authToken)
            contentType(ContentType.Application.Json)
            setBody(createComplimentRequest)
        }.body<CreateComplimentResponse>())
    }.getOrElse(ThrowableToResultMapper::mapThrowable)
    
    override suspend fun deleteCompliment(id: String, authToken: Token): ResponseResult<Unit> =
        runCatching {
            Ok(httpClient.delete(routeProvider.complimentRoute(id)) {
                bearerAuth(authToken)
            }.body<Unit>())
        }.getOrElse(ThrowableToResultMapper::mapThrowable)
    
    override suspend fun getCompliments(
        limit: Int,
        offset: Int,
        authorId: String?,
    ): ResponseResult<GetComplimentsResponse> = runCatching {
        Ok(httpClient.get(routeProvider.complimentsRoute) {
            url {
                parametersOf(
                    LIMIT to limit,
                    OFFSET to offset,
                )
                authorId?.let { parameters[AUTHOR] = it }
            }
        }.body<GetComplimentsResponse>())
    }.getOrElse(ThrowableToResultMapper::mapThrowable)
    
    override suspend fun getRandomCompliment(timeZone: TimeZone): ResponseResult<GetComplimentResponse> =
        runCatching {
            Ok(httpClient.get(routeProvider.randomComplimentRoute){
                parameter("refresh-data", System.now().toLocalDateTime(timeZone).toString())
            }.body<GetComplimentResponse>())
        }.getOrElse(ThrowableToResultMapper::mapThrowable)
    
    override suspend fun like(id: String, authToken: Token): ResponseResult<Unit> = runCatching {
        Ok(httpClient.post(routeProvider.like(id)) {
            bearerAuth(authToken)
        }.body<Unit>())
    }.getOrElse(ThrowableToResultMapper::mapThrowable)
    
    private object ComplimentsParameters {
        const val LIMIT = "limit"
        const val OFFSET = "offset"
        const val AUTHOR = "author"
    }
    
}