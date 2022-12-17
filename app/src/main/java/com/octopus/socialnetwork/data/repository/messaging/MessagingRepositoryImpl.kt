package com.octopus.socialnetwork.data.repository.messaging

import com.octopus.socialnetwork.data.remote.response.dto.messages.*
import com.octopus.socialnetwork.data.remote.service.SocialService
import javax.inject.Inject

class MessagingRepositoryImpl @Inject constructor(
    private val service: SocialService,
) : MessagingRepository {

    override suspend fun getRecentMassagesList(messageReceiver: Int): MessageListDto {
        return service.getMessagesListRecent(messageReceiver).result
    }

    override suspend fun sendMessage(
        messageSenderId: Int,
        messageReceiverId: Int,
        message: String
    ): MessageDto {
        return service.sendMessage(messageSenderId, messageReceiverId, message).result
    }

    override suspend fun unreadMessages(
        messageSenderId: Int,
        messageReceiverId: Int
    ): MessageListDto {
        return service.unreadMessages(messageSenderId, messageReceiverId).result
    }

    override suspend fun getMessages(currentUserId: Int, otherUserId: Int): MessageListDto {
        return service.getMessagesList(currentUserId, otherUserId).result
    }
}

