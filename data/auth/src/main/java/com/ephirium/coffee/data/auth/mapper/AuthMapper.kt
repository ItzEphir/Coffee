package com.ephirium.coffee.data.auth.mapper

import com.ephirium.coffee.data.auth.model.entity.SecretModel
import com.ephirium.coffee.data.auth.model.entity.SignInModel
import com.ephirium.coffee.data.auth.model.entity.SignUpModel
import com.ephirium.coffee.data.auth.model.request.SignInRequest
import com.ephirium.coffee.data.auth.model.request.SignUpRequest
import com.ephirium.coffee.data.auth.model.response.SecretResponse
import kotlinx.datetime.Instant

class AuthMapper {
    
    companion object {
        internal fun SignInModel.toRequest() = SignInRequest(login, password)
        internal fun SignUpModel.toRequest() = SignUpRequest(login, name, password)
        internal fun SecretResponse.toModel() = SecretModel(login, name, agent, Instant.fromEpochMilliseconds(timestamp.toLong()))
    }
    
}