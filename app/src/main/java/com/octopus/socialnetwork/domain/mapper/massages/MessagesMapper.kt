package com.octopus.socialnetwork.domain.mapper.massages

import com.octopus.socialnetwork.data.remote.response.dto.auth.Avatar
import com.octopus.socialnetwork.data.remote.response.dto.massages.MessageDTO
import com.octopus.socialnetwork.data.remote.response.dto.massages.MessageSenderDTO
import com.octopus.socialnetwork.data.remote.response.dto.massages.message_send.SendMessageDTO
import com.octopus.socialnetwork.data.remote.response.dto.massages.unread_message.UnreadMessagesDTO
import com.octopus.socialnetwork.domain.model.massages.messagesRecentList.AvatarSizes
import com.octopus.socialnetwork.domain.model.massages.messagesRecentList.MessageDetails
import com.octopus.socialnetwork.domain.model.massages.messagesRecentList.MessageSender
import com.octopus.socialnetwork.domain.model.massages.messagesRecentList.UnreadMessageDetails


fun MessageDTO.asMassagesDetails(): MessageDetails {
    return MessageDetails(
        message = message ?: "",
        messageFrom = messageFrom?.asMessageTo() ?: MessageSender(0, " ", " ", AvatarSizes(" ")),
        messageTo = messageTo?.asMessageTo() ?: MessageSender(0, " ", " ", AvatarSizes(" "))

    )
}

fun SendMessageDTO.asSendMassagesList(): MessageDetails {
    return MessageDetails(
        message = message ?: "",
        messageFrom = messageFrom?.asMessageTo() ?: MessageSender(0, " ", " ", AvatarSizes(" ")),
        messageTo = messageTo?.asMessageTo() ?: MessageSender(0, " ", " ", AvatarSizes(" "))

    )
}

fun UnreadMessagesDTO.asUnreadMassages(): UnreadMessageDetails {
    return UnreadMessageDetails(
        ifUnread = ifUnread ?: false,
        withUser = withUser?.asMessageTo() ?: MessageSender(0, " ", " ", AvatarSizes(" "))
    )
}


fun MessageSenderDTO.asMessageTo(): MessageSender {
    return MessageSender(
        userId = guid ?: 0,
        fullName = fullName ?: "",
        userName = username ?: "",
        avatar = (avatar?.asAvatar() ?: "") as AvatarSizes
    )
}


fun Avatar.asAvatar(): AvatarSizes {
    return AvatarSizes(
        linkOfSmallAvatar = small ?: " "
    )
}