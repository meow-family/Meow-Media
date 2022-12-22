package com.octopus.socialnetwork.data.mapper

import com.octopus.socialnetwork.data.local.entity.MessageEntity
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageDto


fun MessageDto.asMessageEntity(): MessageEntity {
    return MessageEntity(
        id = id ?: 0,
        answered = answered ?: 0,
        message = message ?: "",
        userId = messageSender?.userId ?: 0,
        fullName = messageSender?.fullName ?: "",
        avatar = messageSender?.avatar?.large ?: "",
        username = messageSender?.username ?: "",
        time = time ?: 0L,
        viewed = viewed ?: "",

    )
}