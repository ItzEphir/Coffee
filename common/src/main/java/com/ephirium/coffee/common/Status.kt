package com.ephirium.coffee.common

sealed interface Status<in T> {
    data class Success<T>(val result: T) : Status<T>
    
    data object NetworkError : Status<Any>
    
    data object TimeoutError : Status<Any>
    
    data object Error : Status<Any>
}