package com.octopus.socialnetwork.domain.mapper.messages

import com.octopus.socialnetwork.data.local.entity.ConversationEntity
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageDto
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageUserDto
import com.octopus.socialnetwork.domain.model.messages.MessageUser
import com.octopus.socialnetwork.domain.model.messages.Messages
import com.octopus.socialnetwork.domain.utils.toFormattedDate
import com.octopus.socialnetwork.ui.util.extensions.getHourAndMinutes
import java.util.Calendar

fun MessageDto.toMessages(userId: Int): Messages {
    return Messages(
        message = message ?: "",
        otherUser = if (messageSender?.userId == userId) messageReceiver?.toMessageUser()
            ?: MessageUser() else messageSender?.toMessageUser() ?: MessageUser(),
        time = time.toFormattedDate(),
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

fun MessageDto.toConversationsEntity(myUserId: Int): ConversationEntity {
    return ConversationEntity(
        lastMessage = message ?: "",
        friendId = if (messageSender?.userId == myUserId) messageReceiver?.toMessageUser()?.userId
            ?: 0 else messageSender?.toMessageUser()?.userId ?: 0,
        time = time.toFormattedDate().getHourAndMinutes(),
        )
}

fun ConversationEntity.toMessages() : Messages {
    return Messages(
        message = lastMessage,
        time = Calendar.getInstance().time,
        isSentByMe = false,
        otherUser = MessageUser(userId = friendId)
    )
}