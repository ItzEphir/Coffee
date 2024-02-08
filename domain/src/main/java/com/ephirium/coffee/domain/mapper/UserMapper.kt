package com.ephirium.coffee.domain.mapper

import com.ephirium.coffee.domain.model.dto.UserDTOBase
import com.ephirium.coffee.domain.model.present.User

fun UserDTOBase.convertForPresentation() = User(id, user, token)
