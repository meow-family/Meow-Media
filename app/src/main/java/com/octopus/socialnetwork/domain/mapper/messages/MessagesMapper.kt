package com.octopus.socialnetwork.domain.mapper.messages

import com.octopus.socialnetwork.data.local.entity.MessageEntity
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageDto
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageUserDto
import com.octopus.socialnetwork.domain.model.messages.Messages
import com.octopus.socialnetwork.domain.model.messages.MessageUser
import com.octopus.socialnetwork.domain.utils.toFormattedDate

fun MessageDto.toMessages(userId: Int): Messages {
    return Messages(
        message = message?: "",
        otherUser = if (messageSender?.userId == userId) messageReceiver?.toMessageUser()
            ?: MessageUser() else messageSender?.toMessageUser() ?: MessageUser(),
        time = time.toFormattedDate(),
        viewed = viewed ?: "",
        isSentByMe = userId == messageSender?.userId,
    )
}

fun MessageUserDto.toMessageUser(): MessageUser {
    return MessageUser(
        userId = userId ?: 0,
        fullName = fullName ?: "",
        userName = username ?: "",
        avatar = avatar?.larger ?: ""
    )
}

fun MessageEntity.entityToMessages(): Messages {
    return Messages(
        message = message,
        otherUser = MessageUser(userId = messageUserId, fullName = fullName, avatar = avatar),
        time = time.toFormattedDate(),
        viewed = viewed,
        isSentByMe = isSentByMe,
    )
}