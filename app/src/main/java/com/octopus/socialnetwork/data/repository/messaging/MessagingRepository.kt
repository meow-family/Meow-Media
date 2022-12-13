package com.octopus.socialnetwork.data.repository.messaging

import com.octopus.socialnetwork.data.remote.response.dto.messages.*

interface MessagingRepository {
    suspend fun getRecentMassagesList(messageReceiver: Int): MessageListDto

    suspend fun sendMessage(
        messageSenderId: Int,
        messageReceiverId: Int,
        message: String,
    ): MessageDto

    suspend fun unreadMessages(
        messageSenderId: Int,
        messageReceiverId: Int,
        markAllRead: Int,
    ): MessageListDto

    suspend fun getMessages(currentUserId: Int, otherUserId: Int): MessageListDto
}