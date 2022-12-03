package com.octopus.socialnetwork.domain.mapper.messages

import com.octopus.socialnetwork.data.remote.response.dto.auth.Avatar
import com.octopus.socialnetwork.data.remote.response.dto.messages.Message
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageUser
import com.octopus.socialnetwork.data.remote.response.dto.messages.message_send.SendMessageDTO
import com.octopus.socialnetwork.data.remote.response.dto.messages.unread_message.UnreadMessagesDTO
import com.octopus.socialnetwork.domain.model.messages.messages_recent_list.MessageDetails
import com.octopus.socialnetwork.domain.model.messages.messages_recent_list.UnreadMessageDetails


//fun Message.asMassagesDetails(): MessageDetails {
//    return MessageDetails(
//        message = message ?: "",
//        messageSender = messageSender?.asMessageTo()
//            ?: com.octopus.socialnetwork.domain.model.messages.messages_recent_list.MessageUser(
//                0, " ", " ",
//                com.octopus.socialnetwork.domain.model.messages.messages_recent_list.Avatar(" ")
//            ),
//        messageReceiver = messageReceiver?.asMessageTo()
//            ?: com.octopus.socialnetwork.domain.model.messages.messages_recent_list.MessageUser(
//                0, " ", " ",
//                com.octopus.socialnetwork.domain.model.messages.messages_recent_list.Avatar(" ")
//            )
//
//    )
//}
//
//fun SendMessageDTO.asSendMassagesList(): MessageDetails {
//    return MessageDetails(
//        message = message ?: "",
//        messageSender = messageSender?.asMessageTo()
//            ?: com.octopus.socialnetwork.domain.model.messages.messages_recent_list.MessageUser(
//                0, " ", " ",
//                com.octopus.socialnetwork.domain.model.messages.messages_recent_list.Avatar(" ")
//            ),
//        messageReceiver = messageReceiver?.asMessageTo()
//            ?: com.octopus.socialnetwork.domain.model.messages.messages_recent_list.MessageUser(
//                0, " ", " ",
//                com.octopus.socialnetwork.domain.model.messages.messages_recent_list.Avatar(" ")
//            )
//
//    )
//}
//
//fun UnreadMessagesDTO.asUnreadMassages(): UnreadMessageDetails {
//    return UnreadMessageDetails(
////        ifUnread = messages ?: emptyList<Message>(),
//        messageReceiver = messageReceiver?.asMessageTo()
//            ?: com.octopus.socialnetwork.domain.model.messages.messages_recent_list.MessageUser(
//                0, " ", " ",
//                com.octopus.socialnetwork.domain.model.messages.messages_recent_list.Avatar(" ")
//            )
//    )
//}


//fun MessageUser.asMessageTo(): com.octopus.socialnetwork.domain.model.messages.messages_recent_list.MessageUser {
//    return com.octopus.socialnetwork.domain.model.messages.messages_recent_list.MessageUser(
//        userId = userId ?: 0,
//        fullName = fullName ?: "",
//        userName = username ?: "",
//        avatar = (avatar?.asAvatar()
//            ?: "") as com.octopus.socialnetwork.domain.model.messages.messages_recent_list.Avatar
//    )
//}


//fun Avatar.asAvatar(): Avatar {
//    return Avatar(
//        linkOfSmallAvatar = small ?: " "
//    )
//}