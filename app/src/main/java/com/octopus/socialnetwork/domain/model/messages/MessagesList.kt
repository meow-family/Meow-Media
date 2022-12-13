package com.octopus.socialnetwork.domain.model.messages

data class MessagesList(
    val messageReceiver : MessageUser,
    val messages :List<MessageDetails>
)
