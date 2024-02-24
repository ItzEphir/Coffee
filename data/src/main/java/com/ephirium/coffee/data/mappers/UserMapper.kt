package com.ephirium.coffee.data.mappers

import com.ephirium.coffee.databases.users.models.UserDTO
import com.ephirium.coffee.domain.model.present.User

fun UserDTO.map(): User = User(id = id, user = user, devices = devices)