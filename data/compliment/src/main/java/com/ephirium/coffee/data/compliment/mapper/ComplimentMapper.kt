package com.ephirium.coffee.data.compliment.mapper

import com.ephirium.coffee.data.compliment.model.entity.Compliment
import com.ephirium.coffee.data.compliment.model.entity.ComplimentShort
import com.ephirium.coffee.data.compliment.model.entity.Compliments
import com.ephirium.coffee.data.compliment.model.request.CreateComplimentRequest
import com.ephirium.coffee.data.compliment.model.request.UpdateComplimentRequest
import com.ephirium.coffee.data.compliment.model.response.CreateComplimentResponse
import com.ephirium.coffee.data.compliment.model.response.GetComplimentResponse
import com.ephirium.coffee.data.compliment.model.response.GetComplimentsResponse

internal fun GetComplimentResponse.toCompliment() =
    Compliment(id, text, language, authorId, likedIds)

internal fun CreateComplimentResponse.toCompliment() =
    Compliment(id, text, language, authorId, likedIds)

internal fun GetComplimentsResponse.toCompliments() =
    Compliments(isEnd, results.map { it.toCompliment() })

internal fun ComplimentShort.toUpdateComplimentRequest() = UpdateComplimentRequest(text, language)

internal fun ComplimentShort.toCreateComplimentRequest() = CreateComplimentRequest(text, language)