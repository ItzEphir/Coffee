package com.ephirium.coffee.data.compliment.service

import com.ephirium.coffee.core.result.ResponseResult
import com.ephirium.coffee.data.compliment.model.dto.Token
import com.ephirium.coffee.data.compliment.model.request.CreateComplimentRequest
import com.ephirium.coffee.data.compliment.model.request.UpdateComplimentRequest
import com.ephirium.coffee.data.compliment.model.response.CreateComplimentResponse
import com.ephirium.coffee.data.compliment.model.response.GetComplimentResponse
import com.ephirium.coffee.data.compliment.model.response.GetComplimentsResponse
import kotlinx.datetime.TimeZone

internal interface ComplimentService {
    
    suspend fun getCompliment(id: String): ResponseResult<GetComplimentResponse>
    
    suspend fun updateCompliment(
        id: String,
        authToken: Token,
        updateComplimentRequest: UpdateComplimentRequest,
    ): ResponseResult<Unit>
    
    suspend fun createCompliment(
        authToken: Token,
        createComplimentRequest: CreateComplimentRequest,
    ): ResponseResult<CreateComplimentResponse>
    
    suspend fun deleteCompliment(
        id: String,
        authToken: Token,
    ): ResponseResult<Unit>
    
    suspend fun getCompliments(
        limit: Int,
        offset: Int,
        authorId: String? = null,
    ): ResponseResult<GetComplimentsResponse>
    
    suspend fun getRandomCompliment(timeZone: TimeZone): ResponseResult<GetComplimentResponse>
    
    suspend fun like(id: String, authToken: Token): ResponseResult<Unit>
    
}