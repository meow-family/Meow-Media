package com.octopus.socialnetwork.domain.mapper.messages

import com.octopus.socialnetwork.constants.Constants.INVALID_USER_ID
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageDto
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageListDto
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageUserDto
import com.octopus.socialnetwork.domain.model.messages.MessageDetails
import com.octopus.socialnetwork.domain.model.messages.MessageUser
import com.octopus.socialnetwork.domain.model.messages.MessagesList
import com.octopus.socialnetwork.ui.util.extensions.removeHtmlEncoding
import com.octopus.socialnetwork.ui.util.extensions.toFormattedDate

fun MessageDto.toMessageDetails(userId: Int): MessageDetails {
    return MessageDetails(
        message = message?.removeHtmlEncoding() ?: "",
        otherUser = if (messageSender?.userId == userId) messageReceiver?.toMessageUser()
            ?: MessageUser() else messageSender?.toMessageUser() ?: MessageUser(),
        time = time.toFormattedDate(),
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
        userId = userId ?: INVALID_USER_ID,
        fullName = fullName ?: "",
        userName = username ?: "",
        avatar = avatar?.larger ?: ""
    )
}