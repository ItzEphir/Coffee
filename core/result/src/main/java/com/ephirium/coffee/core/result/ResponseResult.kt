package com.ephirium.coffee.core.result

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
sealed interface ResponseResult<in T> {
    @Serializable
    data class Ok<T>(val data: T) : ResponseResult<T>
    
    @Serializable
    sealed interface Failure : ResponseResult<Any> {
        @Contextual
        val throwable: Throwable
        
        @Serializable
        sealed class HttpResponseFailure(open val code: Int) : Failure {
            
            data class Redirection(
                @Contextual override val throwable: Throwable,
                override val code: Int,
            ) : HttpResponseFailure(code) // 3xx
            
            data class ClientError(
                @Contextual override val throwable: Throwable,
                override val code: Int,
            ) : HttpResponseFailure(code) // 4xx
            
            data class ServerError(
                @Contextual override val throwable: Throwable,
                override val code: Int,
            ) : HttpResponseFailure(code) // 5xx
        }
        
        @Serializable
        data class TimeoutError(@Contextual override val throwable: Throwable) : Failure
        
        @Serializable
        data class NoInternetError(@Contextual override val throwable: Throwable) : Failure
        
        @Serializable
        data class Error(@Contextual override val throwable: Throwable) : Failure
    }
}