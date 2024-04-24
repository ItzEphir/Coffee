package com.ephirium.coffee.data.auth.mapper

import com.ephirium.coffee.data.auth.model.entity.SecretModel
import com.ephirium.coffee.data.auth.model.entity.SignInModel
import com.ephirium.coffee.data.auth.model.entity.SignUpModel
import com.ephirium.coffee.data.auth.model.request.SignInRequest
import com.ephirium.coffee.data.auth.model.request.SignUpRequest
import com.ephirium.coffee.data.auth.model.response.SecretResponse
import kotlinx.datetime.Instant
import kotlinx.datetime.Instant.Companion
import kotlinx.datetime.format.DateTimeFormat
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

class AuthMapper {
    
    companion object {
        
        internal fun SignInRequest.toModel() = SignInModel(login, password)
        internal fun SignInModel.toRequest() = SignInRequest(login, password)
        internal fun SignUpRequest.toModel() = SignUpModel(login, name, password)
        internal fun SignUpModel.toRequest() = SignUpRequest(login, name, password)
        
        internal fun SecretResponse.toModel() = SecretModel(login, name, agent, Instant.fromEpochMilliseconds(timestamp.toLong()))
    }
    
}