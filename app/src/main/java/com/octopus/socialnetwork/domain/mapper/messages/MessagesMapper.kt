package com.octopus.socialnetwork.domain.mapper.messages

import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageDto
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageListDto
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageUserDto
import com.octopus.socialnetwork.domain.model.messages.MessageDetails
import com.octopus.socialnetwork.domain.model.messages.MessageUser
import com.octopus.socialnetwork.domain.model.messages.MessagesList


fun MessageDto.toMessageDetails(userId: Int): MessageDetails {
    return MessageDetails(
        userId = id ?: 0,
        message = message ?: "",
        messageSender = messageSender?.toMessageUser() ?: MessageUser(0, " ", " ", " "),
        messageReceiver = messageReceiver?.toMessageUser() ?: MessageUser(0, " ", " ", " "),
        time = time ?: 0,
        viewed = viewed ?: "",
        isSentByMe = userId == messageSender?.userId,
    )
}

fun MessageListDto.toMessagesList(): MessagesList {
    return MessagesList(
        messageReceiver = messageReceiver?.toMessageUser() ?: MessageUser(0, "", "", ""),
        messages = messages?.map { it.toMessageDetails(16) } ?: emptyList()
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