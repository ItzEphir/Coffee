package com.ephirium.coffee.data.repository

import com.ephirium.coffee.data.common.ComplimentDto
import com.ephirium.coffee.data.storage.Database
import com.ephirium.coffee.domain.model.ComplimentDtoBase
import com.ephirium.coffee.domain.repository.ComplimentRepositoryBase
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

internal class ComplimentRepositoryImpl : ComplimentRepositoryBase {
    private val compliments = Database.compliments

    override suspend fun getCompliments(onException: (exception: Exception) -> Unit): Flow<ComplimentDtoBase> =
        callbackFlow {
            val registration = compliments.addSnapshotListener { snapshot, e ->
                e?.let {
                    close(it)
                    onException(it)
                }
                snapshot?.documents?.forEach { document ->
                    document.toObject<ComplimentDto>()?.let {
                        launch {
                            send(it)
                        }
                    }
                }
            }

            awaitClose {
                registration.remove()
            }
        }
}