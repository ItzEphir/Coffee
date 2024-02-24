package com.ephirium.coffee.common

import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext
import kotlin.time.Duration

suspend inline fun runTimeout(timeout: Duration, crossinline block: suspend () -> Unit): Result<Unit> {
    return CoroutineScope(coroutineContext).async {
        try {
            withTimeout(timeout) {
                block()
                Result.success(Unit)
            }
        } catch (exception: TimeoutCancellationException) {
             Result.failure(exception)
        }
    }.await()
}