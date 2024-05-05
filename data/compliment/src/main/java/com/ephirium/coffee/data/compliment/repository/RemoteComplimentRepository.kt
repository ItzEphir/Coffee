package com.ephirium.coffee.data.compliment.repository

import com.ephirium.coffee.core.result.ResponseResult
import com.ephirium.coffee.core.result.ResponseResult.Failure.Error
import com.ephirium.coffee.core.result.map
import com.ephirium.coffee.data.auth_token.repository.TokenRepository
import com.ephirium.coffee.data.compliment.mapper.toCompliment
import com.ephirium.coffee.data.compliment.mapper.toCompliments
import com.ephirium.coffee.data.compliment.mapper.toCreateComplimentRequest
import com.ephirium.coffee.data.compliment.mapper.toUpdateComplimentRequest
import com.ephirium.coffee.data.compliment.model.entity.Compliment
import com.ephirium.coffee.data.compliment.model.entity.ComplimentShort
import com.ephirium.coffee.data.compliment.model.entity.Compliments
import com.ephirium.coffee.data.compliment.service.ComplimentService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.datetime.TimeZone

internal class RemoteComplimentRepository(
    private val complimentService: ComplimentService,
    private val tokenRepository: TokenRepository,
) : ComplimentRepository {
    override suspend fun getCompliment(id: String): Flow<ResponseResult<Compliment>> = flow {
        emit(complimentService.getCompliment(id).map { it.toCompliment() })
    }.flowOn(Dispatchers.IO)
    
    override suspend fun updateCompliment(
        id: String,
        complimentShort: ComplimentShort,
    ): Flow<ResponseResult<Unit>> = flow<ResponseResult<Unit>> {
        val token = getToken() ?: return@flow
        val updateComplimentRequest = complimentShort.toUpdateComplimentRequest()
        emit(complimentService.updateCompliment(id, token, updateComplimentRequest))
    }.flowOn(Dispatchers.IO)
    
    override suspend fun createCompliment(complimentShort: ComplimentShort): Flow<ResponseResult<Compliment>> =
        flow<ResponseResult<Compliment>> {
            val token = getToken() ?: return@flow
            val createComplimentRequest = complimentShort.toCreateComplimentRequest()
            emit(complimentService.createCompliment(token, createComplimentRequest)
                .map { it.toCompliment() })
        }.flowOn(Dispatchers.IO)
    
    override suspend fun deleteCompliment(id: String): Flow<ResponseResult<Unit>> =
        flow<ResponseResult<Unit>> {
            val token = getToken() ?: return@flow
            emit(complimentService.deleteCompliment(id, token))
        }.flowOn(Dispatchers.IO)
    
    override suspend fun getCompliments(page: Int): Flow<ResponseResult<Compliments>> = flow {
        val (offset, limit) = getLimitAndOffset(page)
        emit(complimentService.getCompliments(limit, offset).map { it.toCompliments() })
    }.flowOn(Dispatchers.IO)
    
    override suspend fun getComplimentsByAuthor(
        authorId: String,
        page: Int,
    ): Flow<ResponseResult<Compliments>> = flow {
        val (offset, limit) = getLimitAndOffset(page)
        emit(complimentService.getCompliments(limit, offset, authorId).map { it.toCompliments() })
    }.flowOn(Dispatchers.IO)
    
    override suspend fun getRandomCompliment(timeZone: TimeZone): Flow<ResponseResult<Compliment>> = flow {
        emit(complimentService.getRandomCompliment(timeZone).map { it.toCompliment() })
    }.flowOn(Dispatchers.IO)
    
    override suspend fun like(id: String): Flow<ResponseResult<Unit>> = flow<ResponseResult<Unit>> {
        val token = getToken() ?: return@flow
        emit(complimentService.like(id, token))
    }.flowOn(Dispatchers.IO)
    
    @Suppress("UNCHECKED_CAST")
    private suspend inline fun <reified T> FlowCollector<ResponseResult<T>>.getToken() =
        runCatching {
            tokenRepository.getToken() ?: throw NullPointerException("Token is null")
        }.getOrElse {
            emit(Error(it) as ResponseResult<T>)
            null
        }
    
    private companion object {
        const val PER_PAGE = 10
        
        fun getLimitAndOffset(page: Int) = (page - 1) * PER_PAGE to PER_PAGE
    }
}