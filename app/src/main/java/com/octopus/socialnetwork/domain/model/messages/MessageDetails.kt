package com.octopus.socialnetwork.domain.model.messages

data class MessageDetails(
    val userId: Int,
    val messageSender: MessageUser,
    val messageReceiver: MessageUser,
    val message: String,
    val time: Int,
    val viewed: String,
)