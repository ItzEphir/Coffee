package com.ephirium.coffee.domain.usecase

import com.ephirium.coffee.domain.model.ComplimentDtoBase
import com.ephirium.coffee.domain.repository.ComplimentRepositoryBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GetComplimentsUseCase(private val complimentRepositoryBase: ComplimentRepositoryBase) {

    fun getCompliments(): List<ComplimentDtoBase>? = try {
        complimentRepositoryBase.getCompliments()
    } catch (exception: Exception) {
        null
    }

    suspend fun execute(
        onReceiveListener: (List<ComplimentDtoBase>) -> Unit,
        onExceptionListener: (exception: Exception) -> Unit,
    ) {
        withContext(Dispatchers.IO) {
            launch {
                try {
                    onReceiveListener(complimentRepositoryBase.getCompliments())
                } catch (exception: Exception) {
                    onExceptionListener(exception)
                }
            }
        }
    }
}