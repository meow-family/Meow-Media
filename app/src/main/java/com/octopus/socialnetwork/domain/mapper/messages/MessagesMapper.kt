package com.octopus.socialnetwork.domain.mapper.messages

import com.octopus.socialnetwork.data.local.entity.ConversationEntity
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageDto
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageUserDto
import com.octopus.socialnetwork.domain.model.messages.MessageUser
import com.octopus.socialnetwork.domain.model.messages.Messages
import com.octopus.socialnetwork.domain.utils.toFormattedDate
import java.util.*

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
    val otherUser = if (messageSender?.userId == myUserId) messageReceiver?.toMessageUser()
        ?: MessageUser() else messageSender?.toMessageUser() ?: MessageUser()
    return ConversationEntity(
        lastMessage = message ?: "",
        friendId = otherUser.userId,
        friendName = otherUser.fullName,
        avatar = otherUser.avatar,
        time = this.time ?: Calendar.getInstance().time.time,
    )
}

fun ConversationEntity.toMessages(): Messages {
    return Messages(
        message = lastMessage,
        time = Date(time),
        isSentByMe = false,
        otherUser = MessageUser(userId = friendId, fullName = friendName, avatar = avatar)
    )
}