package com.ephirium.coffee.core.result

import com.ephirium.coffee.core.result.ResponseResult.Failure
import com.ephirium.coffee.core.result.ResponseResult.Ok

/**
 * Change result data
 *
 * @param T type of first result
 * @param R type of second result
 * @param block map block
 * @return Result with type R
 */
@Suppress("UNCHECKED_CAST")
inline fun <reified T, reified R> ResponseResult<T>.map(block: (T) -> R) = when (this) {
    is Ok -> Ok(block(data))
    else  -> this as ResponseResult<R>
}

/**
 * Map element from result to another result
 *
 * @param T type of first result
 * @param R type of second result
 * @param block mapper
 * @return Result with type R
 */
@Suppress("UNCHECKED_CAST")
inline fun <reified T, reified R> ResponseResult<T>.flatMap(block: (T) -> ResponseResult<R>) =
    when (this) {
        is Ok -> block(data)
        else  -> this as ResponseResult<R>
    }

/**
 * Run code if result is ok
 *
 * Can be written as:
 *
 * ``result.onOk { println("Successful $it") }``
 *
 * @param T type of result
 * @param block code to run
 * @return Same Result
 */
inline fun <reified T> ResponseResult<T>.onOk(block: (T) -> Unit): ResponseResult<T> {
    if (this is Ok) block(this.data)
    return this
}

/**
 * Run code if result is type T
 *
 * Can be written as:
 *
 * ``result.on<HttpError> { println(it.code) }``
 *
 *
 * @param T type to match
 * @param block code to run
 * @return Same Result
 */
inline fun <reified T : ResponseResult<*>> ResponseResult<*>.on(block: (T) -> Unit): ResponseResult<*> {
    if (this is T) block(this)
    return this
}

/**
 * Run code if result is Failure
 *
 * Can be written as:
 *
 * ``result.onFailure { it.printStackTrace() }``
 *
 * @param block code to run
 * @return Same Result
 */
inline fun ResponseResult<*>.onFailure(block: (Throwable) -> Unit): ResponseResult<*> {
    if (this is Failure) block(throwable)
    return this
}

/**
 * Returns data if result is Ok
 *
 * Can be written as:
 *
 * ``val data = result.getOrNull()``
 *
 * @return value of type T or null
 */
fun <T> ResponseResult<T>.getOrNull(): T? = (this as? Ok)?.data

/**
 * Returns data or throws an exception
 *
 * Can be written as:
 *
 * ``val data = result.getOrThrow()``
 *
 * @return value of type T
 */
fun <T> ResponseResult<T>.getOrThrow(): T = try {
    (this as Ok).data
} catch (classCastException: ClassCastException) {
    throw ResponseResultException("Result is not Ok")
}


/**
 * Returns data
 *
 * Can be written as:
 *
 * ``val data = result.get()``
 *
 * @return value of type T
 */
fun <T> ResponseResult<T>.get(): T = (this as Ok).data
