package com.octopus.socialnetwork.domain.mapper.massages

import com.octopus.socialnetwork.data.remote.response.dto.auth.Icon
import com.octopus.socialnetwork.data.remote.response.dto.massages.MessageDTO
import com.octopus.socialnetwork.data.remote.response.dto.massages.MessageSenderDTO
import com.octopus.socialnetwork.data.remote.response.dto.massages.message_send.SendMessageDTO
import com.octopus.socialnetwork.data.remote.response.dto.massages.unread_message.UnreadMessagesDTO
import com.octopus.socialnetwork.domain.model.massages.messagesRecentList.IconSizes
import com.octopus.socialnetwork.domain.model.massages.messagesRecentList.MessageDetails
import com.octopus.socialnetwork.domain.model.massages.messagesRecentList.MessageSender
import com.octopus.socialnetwork.domain.model.massages.messagesRecentList.UnreadMessageDetails


fun MessageDTO.asMassagesDetails(): MessageDetails {
    return MessageDetails(
        message = message ?: "",
        messageFrom = messageFrom?.asMessageTo() ?: MessageSender(0, " ", " ", IconSizes(" ")),
        messageTo = messageTo?.asMessageTo() ?: MessageSender(0, " ", " ", IconSizes(" "))

    )
}

fun SendMessageDTO.asSendMassagesList(): MessageDetails {
    return MessageDetails(
        message = message ?: "",
        messageFrom = messageFrom?.asMessageTo() ?: MessageSender(0, " ", " ", IconSizes(" ")),
        messageTo = messageTo?.asMessageTo() ?: MessageSender(0, " ", " ", IconSizes(" "))

    )
}

fun UnreadMessagesDTO.asUnreadMassages(): UnreadMessageDetails {
    return UnreadMessageDetails(
        ifUnread = ifUnread ?: false,
        withUser = withUser?.asMessageTo() ?: MessageSender(0, " ", " ", IconSizes(" "))
    )
}


fun MessageSenderDTO.asMessageTo(): MessageSender {
    return MessageSender(
        userId = guid ?: 0,
        fullName = fullName ?: "",
        userName = username ?: "",
        icon = (icon?.asIcon() ?: "") as IconSizes
    )
}


fun Icon.asIcon(): IconSizes {
    return IconSizes(
        linkOfSmallImage = small ?: " "
    )
}