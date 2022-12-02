package com.octopus.socialnetwork.data.repository.messaging

import com.octopus.socialnetwork.data.remote.response.dto.massages.list_messages.MessageListDTO
import com.octopus.socialnetwork.data.remote.response.dto.massages.message_send.SendMessageDTO
import com.octopus.socialnetwork.data.remote.response.dto.massages.recent_messages.RecentMessagesDTO
import com.octopus.socialnetwork.data.remote.response.dto.massages.unread_message.UnreadMessagesDTO

interface MessagingRepository {
    suspend fun getRecentMassagesList(visitedUserId: Int): RecentMessagesDTO

    suspend fun sendMessage(from: Int, to: Int, message: String): SendMessageDTO

    suspend fun unreadMessages(from: Int, to: Int, unreadMessage: String): UnreadMessagesDTO

    suspend fun messageList(userId: Int, to: Int):MessageListDTO

}