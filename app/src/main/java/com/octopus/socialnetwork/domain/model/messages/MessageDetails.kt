package com.octopus.socialnetwork.domain.model.messages

import java.util.Date

data class MessageDetails(
    val userId: Int,
    val messageSender: MessageUser,
    val messageReceiver: MessageUser,
    val message: String,
    val time: Date,
    val viewed: String,
    val isSentByMe: Boolean,
)