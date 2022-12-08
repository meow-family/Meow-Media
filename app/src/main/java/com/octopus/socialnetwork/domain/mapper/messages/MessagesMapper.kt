package com.octopus.socialnetwork.domain.mapper.messages

import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageDto
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageUserDto
import com.octopus.socialnetwork.data.remote.response.dto.messages.SendMessageDto
import com.octopus.socialnetwork.data.remote.response.dto.messages.UnreadMessagesDto
import com.octopus.socialnetwork.domain.model.messages.MessageDetails
import com.octopus.socialnetwork.domain.model.messages.MessageUser
import com.octopus.socialnetwork.domain.model.messages.UnreadMessageDetails


fun MessageDto.asMassagesDetails(): MessageDetails {
    return MessageDetails(
        userId = id ?: 0,
        message = message ?: "",
        messageSender = messageSender?.asMessageTo()
            ?: MessageUser(
                0, " ", " ",
                " "
            ),
        messageReceiver = messageReceiver?.asMessageTo()
            ?: MessageUser(
                0, " ", " ", " "
            ),
        time = time ?: 0,
        viewed = viewed ?: "",

        )
}

fun SendMessageDto.asSendMassagesList(): MessageDetails {
    return MessageDetails(
        userId = id ?: 0,
        message = message ?: "",
        messageSender = messageSender?.asMessageTo()
            ?: MessageUser(
                0, " ", " ", ""
            ),
        messageReceiver = messageReceiver?.asMessageTo()
            ?: MessageUser(
                0, " ", " ", ""
            ),
        time = time ?: 0,
        viewed = viewed ?: "",
    )
}

fun UnreadMessagesDto.asUnreadMassages(): UnreadMessageDetails {
    return UnreadMessageDetails(
        messageReceiver = messageReceiver?.asMessageTo()
            ?: MessageUser(
                0, "", "", ""
            )
    )
}


fun MessageUserDto.asMessageTo(): MessageUser {
    return MessageUser(
        userId = userId ?: 0,
        fullName = fullName ?: "",
        userName = username ?: "",
        avatar = avatar?.larger ?: ""
    )
}