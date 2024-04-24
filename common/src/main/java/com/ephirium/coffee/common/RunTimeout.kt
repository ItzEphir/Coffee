package com.ephirium.coffee.common

import com.ephirium.coffee.common.TimeoutResult.TimeoutFailure
import com.ephirium.coffee.common.TimeoutResult.TimeoutOk
import kotlinx.coroutines.*
import kotlin.time.Duration

@Suppress("UNCHECKED_CAST")
suspend inline fun<reified T> runTimeout(
    timeout: Duration,
    crossinline block: suspend CoroutineScope.() -> T
) = coroutineScope {
    async {
        try {
            withTimeout(timeout) {
                TimeoutOk(block())
            }
        } catch (exception: TimeoutCancellationException) {
            TimeoutFailure(exception) as TimeoutResult<T>
        }
    }.await()
}