package com.ephirium.coffee.common

import com.ephirium.coffee.common.Status.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlin.reflect.KClass

inline fun <T> Status<T>.onSuccess(block: (T) -> Unit): Status<T> {
    if (this is Success) block(this.result)
    return this
}

inline fun <T> Status<T>.onFailure(block: (KClass<*>) -> Unit): Status<T> {
    if (this !is Success) block(this::class)
    return this
}

fun <T> Status<T>.onNetworkError(block: () -> Unit) = onErrorType<T, NetworkError>(block)
fun <T> Status<T>.onTimeoutError(block: () -> Unit) = onErrorType<T, TimeoutError>(block)
fun <T> Status<T>.onError(block: () -> Unit) = onErrorType<T, Error>(block)

suspend inline fun <T, R> Status<T>.map(crossinline block: suspend (T) -> R) = when (this) {
    is Success      -> Success(block(this.result))
    is NetworkError -> NetworkError
    is TimeoutError -> TimeoutError
    is Error        -> Error
}

suspend inline fun <reified T, reified R> Status<T>.flatMap(crossinline block: suspend (T) -> Flow<Status<R>>) =
    when (this) {
        is Success      -> block(this.result)
        is NetworkError -> flowOf(NetworkError)
        is TimeoutError -> flowOf(TimeoutError)
        is Error        -> flowOf(Error)
    }

private inline fun <T, reified R : Status<*>> Status<T>.onErrorType(block: () -> Unit): Status<T> {
    if (this is R) block()
    return this
}