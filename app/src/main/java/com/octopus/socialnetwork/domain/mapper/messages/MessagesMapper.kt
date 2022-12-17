package com.octopus.socialnetwork.domain.mapper.messages

import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageDto
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageListDto
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageUserDto
import com.octopus.socialnetwork.domain.model.messages.MessageDetails
import com.octopus.socialnetwork.domain.model.messages.MessageUser
import com.octopus.socialnetwork.domain.model.messages.MessagesList
import com.octopus.socialnetwork.ui.util.convertLongToDate

fun MessageDto.toMessageDetails(userId: Int): MessageDetails {
    return MessageDetails(
        userId = id ?: 0,
        message = message ?: "",
        messageSender = messageSender?.toMessageUser() ?: MessageUser(),
        messageReceiver = messageReceiver?.toMessageUser() ?: MessageUser(),
        time =  convertLongToDate(time ?: 0),
        viewed = viewed ?: "",
        isSentByMe = userId == messageSender?.userId,
    )
}

fun MessageListDto.toMessagesList(userId: Int): MessagesList {
    return MessagesList(
        messageReceiver = messageReceiver?.toMessageUser() ?: MessageUser(),
        messages = messages?.map { it.toMessageDetails(userId) } ?: emptyList()
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