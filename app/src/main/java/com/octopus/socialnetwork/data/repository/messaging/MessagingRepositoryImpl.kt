package com.octopus.socialnetwork.data.repository.messaging

import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageDto
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageResponse
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageNotificationDto
import com.octopus.socialnetwork.data.remote.response.dto.messages.NotificationData
import com.octopus.socialnetwork.data.remote.service.fcm.CloudMessagingService
import com.octopus.socialnetwork.data.remote.service.fcm.FirebaseCloudMessagingService
import com.octopus.socialnetwork.data.remote.service.apiService.SocialService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MessagingRepositoryImpl @Inject constructor(
    private val service: SocialService,
    private val cloudMessagingService: CloudMessagingService,
) : MessagingRepository {

    override suspend fun getRecentMassagesList(messageReceiver: Int): MessageResponse {
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
        messageReceiverId: Int,
        markAllRead: Int
    ): MessageResponse {
        return service.unreadMessages(messageSenderId, messageReceiverId, markAllRead).result
    }

    override suspend fun getMessages(myUserId: Int, friendId: Int, page: Int): MessageResponse {
        return service.getMessagesList(myUserId, friendId, page).result
    }


    override suspend fun postNotification(notification: MessageNotificationDto): Boolean {
        return cloudMessagingService.postNotification(notification).isSuccessful
    }

    override fun onReceiveNotification(): Flow<NotificationData> {
        return FirebaseCloudMessagingService.events
    }

}

