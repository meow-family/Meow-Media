package com.octopus.socialnetwork.domain.model.messages

data class MessageDetails(
    val messageSender: MessageUser,
    val messageReceiver : MessageUser,
    val message:String,
)