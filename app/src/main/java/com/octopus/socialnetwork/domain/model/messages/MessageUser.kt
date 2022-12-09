package com.octopus.socialnetwork.domain.model.messages

data class MessageUser(
    val userId: Int,
    val fullName: String,
    val userName: String,
    val avatar: String,
)
