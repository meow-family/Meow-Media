package com.octopus.socialnetwork.domain.mapper.message

import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageDTO
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageUserDTO
import com.octopus.socialnetwork.data.remote.response.dto.messages.message_send.SendMessageDTO
import com.octopus.socialnetwork.data.remote.response.dto.messages.recent_messages.RecentMessagesDTO
import com.octopus.socialnetwork.data.remote.response.dto.messages.unread_message.UnreadMessagesDTO
import com.octopus.socialnetwork.domain.model.messages.messages_recent_list.*

fun MessageUserDTO.toMessageUser(): MessageUser {
    return MessageUser(
        userId = userId ?: 0,
        userName = username ?: "",
        fullName = fullName ?: "",
        avatar = Avatar(linkOfSmallAvatar = avatar?.small ?: "")
    )
}

fun MessageDTO.toMessageDetails(): MessageDetails? {

    return messageSender?.let {
        MessageDetails(
            messageSender = it.toMessageUser(),
            messageReceiver = it.toMessageUser(),
            message = message ?: "",
        )
    }
}

fun SendMessageDTO.toSendMessage(): MessageDetails? {
    return messageSender?.let {
        MessageDetails(
            messageSender = it.toMessageUser(),
            messageReceiver = it.toMessageUser(),
            message = message ?: "",
        )
    }
}

//fun RecentMessagesDTO.toRecentMessages(): RecentMessagesList {
//    return RecentMessagesList(
//        massagesDetails = MessageDetails(messages)
//    )
//
//}

fun UnreadMessagesDTO.toUnreadMessages(): UnreadMessageDetails? {
    return messageReceiver?.let {
        UnreadMessageDetails(
            messageReceiver = it.toMessageUser(),
        )
    }
}
