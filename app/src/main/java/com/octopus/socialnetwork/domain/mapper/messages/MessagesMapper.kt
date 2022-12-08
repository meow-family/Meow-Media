package com.octopus.socialnetwork.domain.mapper.messages

import com.octopus.socialnetwork.data.remote.response.dto.Avatar
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageDto
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageUserDto
import com.octopus.socialnetwork.data.remote.response.dto.messages.SendMessageDto
import com.octopus.socialnetwork.data.remote.response.dto.messages.UnreadMessagesDto
import com.octopus.socialnetwork.domain.model.messages.MessageDetails
import com.octopus.socialnetwork.domain.model.messages.MessageUser
import com.octopus.socialnetwork.domain.model.messages.UnreadMessageDetails


fun MessageDto.asMassagesDetails(): MessageDetails {
    return MessageDetails(
        message = message ?: "",
        messageSender = messageSender?.asMessageTo()
            ?: MessageUser(
                0, " ", " ",
                com.octopus.socialnetwork.domain.model.messages.Avatar(" ")
            ),
        messageReceiver = messageReceiver?.asMessageTo()
            ?: MessageUser(
                0, " ", " ",
                com.octopus.socialnetwork.domain.model.messages.Avatar(" ")
            )

    )
}

fun SendMessageDto.asSendMassagesList(): MessageDetails {
    return MessageDetails(
        message = message ?: "",
        messageSender = messageSender?.asMessageTo()
            ?: MessageUser(
                0, " ", " ",
                com.octopus.socialnetwork.domain.model.messages.Avatar(" ")
            ),
        messageReceiver = messageReceiver?.asMessageTo()
            ?: MessageUser(
                0, " ", " ",
                com.octopus.socialnetwork.domain.model.messages.Avatar(" ")
            )

    )
}

fun UnreadMessagesDto.asUnreadMassages(): UnreadMessageDetails {
    return UnreadMessageDetails(
//        ifUnread = messages ?: emptyList<Message>(),
        messageReceiver = messageReceiver?.asMessageTo()
            ?: MessageUser(
                0, " ", " ",
                com.octopus.socialnetwork.domain.model.messages.Avatar(" ")
            )
    )
}


fun MessageUserDto.asMessageTo(): MessageUser {
    return MessageUser(
        userId = userId ?: 0,
        fullName = fullName ?: "",
        userName = username ?: "",
        avatar = (avatar?.asAvatar()
            ?: "") as com.octopus.socialnetwork.domain.model.messages.Avatar
    )
}


fun Avatar.asAvatar(): com.octopus.socialnetwork.domain.model.messages.Avatar {
    return com.octopus.socialnetwork.domain.model.messages.Avatar(
        linkOfSmallAvatar = small ?: " "
    )
}