package com.ephirium.coffee.core.timeout

import com.ephirium.coffee.core.timeout.TimeoutResult.TimeoutFailure
import com.ephirium.coffee.core.timeout.TimeoutResult.TimeoutOk

sealed interface TimeoutResult<in T> {
    data class TimeoutOk<T>(val value: T) : TimeoutResult<T>
    
    data class TimeoutFailure(val throwable: Throwable) : TimeoutResult<Any>
}

suspend inline fun <reified T> TimeoutResult<T>.onOk(crossinline block: suspend (T) -> Unit): TimeoutResult<T> =
    apply {
        (this as? TimeoutOk)?.let { block(value) }
    }

suspend inline fun TimeoutResult<*>.onTimeout(crossinline block: suspend () -> Unit): TimeoutResult<*> =
    apply {
        (this as? TimeoutFailure)?.let { block() }
    }