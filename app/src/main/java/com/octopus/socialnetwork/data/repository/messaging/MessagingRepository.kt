package com.octopus.socialnetwork.data.repository.messaging

import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageDto
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageListDto
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageNotificationDto
import com.octopus.socialnetwork.data.remote.response.dto.messages.NotificationData
import kotlinx.coroutines.flow.Flow

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

    suspend fun getMessages(myUserId: Int, friendId: Int): MessageListDto

    suspend fun postNotification(notification: MessageNotificationDto): Boolean

    fun onReceiveNotification(): Flow<NotificationData>


}