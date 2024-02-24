package com.ephirium.coffee.common

import com.ephirium.coffee.common.TimeoutResult.TimeoutException

sealed interface TimeoutResult<in T> {
    
    data class TimeoutOk<T>(val value: T) : TimeoutResult<T>
    
    data object TimeoutException : TimeoutResult<Any>
}

suspend fun TimeoutResult<*>.onTimeout(block: suspend () -> Unit): TimeoutResult<*> {
    if (this is TimeoutException) {
        block()
    }
    return this
}