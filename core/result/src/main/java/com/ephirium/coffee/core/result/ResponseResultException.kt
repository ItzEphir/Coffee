package com.ephirium.coffee.core.result

class ResponseResultException(
    message: String? = null,
    cause: Throwable? = null,
) : Exception(message, cause)