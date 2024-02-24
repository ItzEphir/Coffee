package com.ephirium.coffee.common

import com.ephirium.coffee.common.TimeoutResult.TimeoutException
import com.ephirium.coffee.common.TimeoutResult.TimeoutOk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.withTimeout
import kotlin.coroutines.coroutineContext
import kotlin.time.Duration

suspend inline fun runTimeout(
    timeout: Duration,
    crossinline block: suspend CoroutineScope.() -> Unit,
) = CoroutineScope(coroutineContext).async {
    try {
        withTimeout(timeout) {
            TimeoutOk(block())
        }
    } catch (exception: TimeoutCancellationException) {
        TimeoutException
    }
}.await()