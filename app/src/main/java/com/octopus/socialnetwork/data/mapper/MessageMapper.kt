package com.octopus.socialnetwork.data.mapper

import com.octopus.socialnetwork.data.local.entity.MessageEntity
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageDto


fun MessageDto.toMessageEntity(userId: Int): MessageEntity {
    val chatOtherUser = if (messageSender?.userId == userId) messageReceiver else messageSender

    return MessageEntity(
        id = chatOtherUser?.userId ?: 0,
        message = message ?: "",
        messageUserId = chatOtherUser?.userId ?: 0,
        fullName = chatOtherUser?.fullName ?: "",
        avatar = chatOtherUser?.avatar?.larger ?: "",
        time = time ?: 0L,
        viewed = viewed ?: "",
        isSentByMe = userId == messageSender?.userId
    )
}