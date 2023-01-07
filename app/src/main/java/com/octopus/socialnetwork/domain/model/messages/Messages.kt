package com.octopus.socialnetwork.domain.model.messages

import java.util.Date

data class Messages(
    val otherUser: MessageUser,
    val message: String,
    val time: Date,
    val isSentByMe: Boolean,
)