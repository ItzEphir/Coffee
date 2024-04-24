package com.ephirium.coffee.core.result

import com.ephirium.coffee.core.result.ResponseResult.Failure.Error
import com.ephirium.coffee.core.result.ResponseResult.Failure.HttpResponseFailure.*
import com.ephirium.coffee.core.result.ResponseResult.Failure.NoInternetError
import com.ephirium.coffee.core.result.ResponseResult.Failure.TimeoutError
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.utils.io.errors.IOException

class ThrowableToResultMapper {
    companion object {
        @Suppress("UNCHECKED_CAST")
        inline fun<reified T> mapThrowable(throwable: Throwable): ResponseResult<T> = when(throwable){
            is RedirectResponseException -> Redirection(throwable, throwable.response.status.value)
            is ClientRequestException -> ClientError(throwable, throwable.response.status.value)
            is ServerResponseException -> ServerError(throwable, throwable.response.status.value)
            is HttpRequestTimeoutException, is ConnectTimeoutException, is SocketTimeoutException -> TimeoutError(throwable)
            is IOException -> NoInternetError(throwable)
            else -> Error(throwable)
        } as ResponseResult<T>
    }
}