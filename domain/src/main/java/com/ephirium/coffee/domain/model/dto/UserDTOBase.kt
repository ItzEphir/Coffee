package com.ephirium.coffee.domain.model.dto

interface UserDTOBase {
    var id: String
    var user: String
    var devices: List<String>
}