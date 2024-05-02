package com.ephirium.coffee.data.compliment.repository

import com.ephirium.coffee.core.result.ResponseResult
import com.ephirium.coffee.data.compliment.model.entity.Compliment
import com.ephirium.coffee.data.compliment.model.entity.ComplimentShort
import com.ephirium.coffee.data.compliment.model.entity.Compliments
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.TimeZone

interface ComplimentRepository {
    suspend fun getCompliment(id: String) : Flow<ResponseResult<Compliment>>
    suspend fun updateCompliment(id: String, complimentShort: ComplimentShort): Flow<ResponseResult<Unit>>
    suspend fun createCompliment(complimentShort: ComplimentShort): Flow<ResponseResult<Compliment>>
    suspend fun deleteCompliment(id: String): Flow<ResponseResult<Unit>>
    suspend fun getCompliments(page: Int): Flow<ResponseResult<Compliments>>
    suspend fun getComplimentsByAuthor(authorId: String, page: Int): Flow<ResponseResult<Compliments>>
    suspend fun getRandomCompliment(timeZone: TimeZone): Flow<ResponseResult<Compliment>>
    suspend fun like(id: String): Flow<ResponseResult<Unit>>
}