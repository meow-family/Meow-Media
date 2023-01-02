package com.octopus.socialnetwork.data.repository.messaging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.octopus.socialnetwork.data.paging.ChatDataSource
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
    private val chatDataSource: ChatDataSource,
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

    override suspend fun getMessagesPager(friendId: Int): Pager<Int, MessageDto> {
        val dataSource = chatDataSource
        dataSource.setFriendID(friendId)
        return Pager(
            config = PagingConfig(5,
                prefetchDistance = 5,enablePlaceholders = true) ,
            pagingSourceFactory = { dataSource })
    }


    override suspend fun postNotification(notification: MessageNotificationDto): Boolean {
        return cloudMessagingService.postNotification(notification).isSuccessful
    }

    override fun onReceiveNotification(): Flow<NotificationData> {
        return FirebaseCloudMessagingService.events
    }

}

