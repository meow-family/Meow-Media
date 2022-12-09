package com.octopus.socialnetwork.data.repository.messaging

import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageListDto
import com.octopus.socialnetwork.data.remote.response.dto.messages.SendMessageDto
import com.octopus.socialnetwork.data.remote.response.dto.messages.RecentMessagesDto
import com.octopus.socialnetwork.data.remote.response.dto.messages.UnreadMessagesDto

interface MessagingRepository {
    suspend fun getRecentMassagesList(messageReceiver: Int): RecentMessagesDto

    suspend fun sendMessage(
        messageSenderId: Int,
        messageReceiverId: Int,
        message: String,
    ): SendMessageDto

    suspend fun unreadMessages(
        messageSenderId: Int,
        messageReceiverId: Int,
        markAllRead: Int,
    ): UnreadMessagesDto

    suspend fun messageList(messageSenderId: Int, messageReceiverId: Int): MessageListDto
}