package com.octopus.socialnetwork.domain.model.messages

data class MessageUser(
    val userId: Int,
    var fullName: String,
    val userName: String,
    val avatar: String,
)
