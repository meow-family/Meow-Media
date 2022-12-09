package com.octopus.socialnetwork.domain.model.messages

data class UnreadMessageDetails(
    val messageReceiver : MessageUser,
    val messages :List<MessageDetails>
)
