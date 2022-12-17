package com.octopus.socialnetwork.domain.model.messages

data class MessageUser(
    val userId: Int = 0,
    val fullName: String = "",
    val userName: String = "",
    val avatar: String = ""
)
