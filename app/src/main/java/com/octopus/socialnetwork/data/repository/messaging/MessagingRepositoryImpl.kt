package com.octopus.socialnetwork.data.repository.messaging

import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageListDto
import com.octopus.socialnetwork.data.remote.response.dto.messages.SendMessageDto
import com.octopus.socialnetwork.data.remote.response.dto.messages.RecentMessagesDto
import com.octopus.socialnetwork.data.remote.response.dto.messages.UnreadMessagesDto
import com.octopus.socialnetwork.data.remote.service.SocialService
import javax.inject.Inject

class MessagingRepositoryImpl @Inject constructor(
    private val service: SocialService,
) : MessagingRepository {

    override suspend fun getRecentMassagesList(messageReceiver: Int): RecentMessagesDto {
        return service.getMessagesListRecent(messageReceiver).result
    }

    override suspend fun sendMessage(
        messageSenderId: Int,
        messageReceiverId: Int,
        message: String
    ): SendMessageDto {
        return service.sendMessage(messageSenderId, messageReceiverId, message).result
    }

    override suspend fun unreadMessages(
        messageSenderId: Int,
        messageReceiverId: Int,
        message: String
    ): UnreadMessagesDto {
        return service.unreadMessages(messageSenderId, messageReceiverId, message).result
    }

    override suspend fun messageList(messageSenderId: Int, messageReceiverId: Int): MessageListDto {
        return service.getMessagesList(messageSenderId, messageReceiverId).result
    }
}

