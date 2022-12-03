package com.octopus.socialnetwork.domain.model.messages.messages_recent_list

data class MessageDetails(
    val messageSender:MessageUser,
    val messageReceiver :MessageUser,
    val message:String,
)