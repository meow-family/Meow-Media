package com.octopus.socialnetwork.data.repository.messaging

import android.util.Log
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageDto
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageListDto
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageNotificationDto
import com.octopus.socialnetwork.data.remote.response.dto.messages.NotificationData
import com.octopus.socialnetwork.data.remote.service.CloudMessagingService
import com.octopus.socialnetwork.data.remote.service.FirebaseCloudMessagingService
import com.octopus.socialnetwork.data.remote.service.SocialService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MessagingRepositoryImpl @Inject constructor(
    private val service: SocialService,
    private val cloudMessagingService: CloudMessagingService,
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
        messageReceiverId: Int,
        markAllRead: Int
    ): MessageListDto {
        return service.unreadMessages(messageSenderId, messageReceiverId, markAllRead).result
    }

    override suspend fun getMessages(currentUserId: Int, otherUserId: Int): MessageListDto {
        return service.getMessagesList(currentUserId, otherUserId).result
    }


    override suspend fun postNotification(notification: MessageNotificationDto): Boolean {
        val isSuccessNotificationPost = cloudMessagingService.postNotification(notification)
        Log.i("TESTING","is successful notification $isSuccessNotificationPost")
        return isSuccessNotificationPost.isSuccessful
    }

    override fun onReceiveNotification(): Flow<NotificationData> {
        return FirebaseCloudMessagingService.events
    }

//    override fun onReceiveMessage(): Flow<MessageDto> {
//        messageFirebaseDataSource.onReceiveNotification()
//    }
}

