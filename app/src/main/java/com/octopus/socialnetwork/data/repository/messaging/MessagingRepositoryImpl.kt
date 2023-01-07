package com.octopus.socialnetwork.data.repository.messaging

import com.octopus.socialnetwork.data.local.dao.ConversationDao
import com.octopus.socialnetwork.data.local.entity.ConversationEntity
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
    private val conversationDao: ConversationDao,
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

    override suspend fun insertConversations(conversations: List<ConversationEntity>) {
        conversationDao.insertConversations(conversations)
    }

    override fun getAllConversations(): Flow<List<ConversationEntity>> {
        return conversationDao.getAllConversations()
    }

    override suspend fun updateConversation(friendId: Int, message: String, time: String) {
        conversationDao.updateConversation(friendId,message,time)
    }

}

