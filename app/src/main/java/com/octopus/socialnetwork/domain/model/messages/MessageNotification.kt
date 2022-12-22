package com.octopus.socialnetwork.domain.model.messages

data class MessageNotification (
    val id: Int,
    val friendId: Int,
    val messageText: String,
    val time: String,
    val to: String,
)