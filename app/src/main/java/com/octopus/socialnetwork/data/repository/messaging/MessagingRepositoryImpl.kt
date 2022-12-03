package com.octopus.socialnetwork.data.repository.messaging

import com.octopus.socialnetwork.data.remote.response.dto.messages.list_messages.MessageListDTO
import com.octopus.socialnetwork.data.remote.response.dto.messages.message_send.SendMessageDTO
import com.octopus.socialnetwork.data.remote.response.dto.messages.recent_messages.RecentMessagesDTO
import com.octopus.socialnetwork.data.remote.response.dto.messages.unread_message.UnreadMessagesDTO
import com.octopus.socialnetwork.data.remote.service.SocialService
import javax.inject.Inject

class MessagingRepositoryImpl @Inject constructor(
    private val service: SocialService,
) : MessagingRepository {

    override suspend fun getRecentMassagesList(messageReceiver: Int): RecentMessagesDTO {
        return service.getMassagesListRecent(messageReceiver).result
    }

    override suspend fun sendMessage(
        messageSender: Int,
        messageReceiver: Int,
        message: String
    ): SendMessageDTO {
        return service.sendMessage(messageSender, messageReceiver, message).result
    }

    override suspend fun unreadMessages(
        messageSender: Int,
        messageReceiver: Int,
        message: String
    ): UnreadMessagesDTO {
        return service.unreadMessages(messageSender, messageReceiver, message).result
    }

    override suspend fun messageList(userId: Int, messageReceiver: Int): MessageListDTO {
        return service.messageList(userId, messageReceiver).result
    }
}

