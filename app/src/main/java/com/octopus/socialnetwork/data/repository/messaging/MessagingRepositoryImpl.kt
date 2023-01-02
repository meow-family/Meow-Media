package com.octopus.socialnetwork.data.repository.messaging

import android.util.Log
import com.octopus.socialnetwork.data.local.dao.ConversationsDao
import com.octopus.socialnetwork.data.local.entity.MessageEntity
import com.octopus.socialnetwork.data.mapper.toMessageEntity
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageDto
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageNotificationDto
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageResponse
import com.octopus.socialnetwork.data.remote.response.dto.messages.NotificationData
import com.octopus.socialnetwork.data.remote.service.apiService.SocialService
import com.octopus.socialnetwork.data.remote.service.fcm.CloudMessagingService
import com.octopus.socialnetwork.data.remote.service.fcm.FirebaseCloudMessagingService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MessagingRepositoryImpl @Inject constructor(
    private val service: SocialService,
    private val cloudMessagingService: CloudMessagingService,
    private val conversationsDao: ConversationsDao,
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

    override suspend fun getMessages(myUserId: Int, friendId: Int): MessageResponse {
        return service.getMessagesList(myUserId, friendId).result
    }


    override suspend fun postNotification(notification: MessageNotificationDto): Boolean {
        return cloudMessagingService.postNotification(notification).isSuccessful
    }

    override fun onReceiveNotification(): Flow<NotificationData> {
        return FirebaseCloudMessagingService.events
    }


    override suspend fun insertConversations(userId: Int) {
        try {
            Log.i("MESSAGING", "will insert messages for user $userId")
            with(service.getMessagesListRecent(userId).result) {
                if (this.count != 0) {
                    this.messages?.map { messageDto ->
                        messageDto.toMessageEntity(userId)
                    }?.let { messagesEntity ->
                        conversationsDao.insertConversations(messagesEntity)
                        Log.i("MESSAGING", "successfully inserted messages")
                    }
                    Log.i("MESSAGING", "done")
                }
            }
        } catch (throwable: Throwable) {
            Log.i("MESSAGING", "encountred this error in ${this.javaClass.simpleName} ${throwable.stackTrace} ")

        }
    }

    override fun getAllConversations(): Flow<List<MessageEntity>> {
        return conversationsDao.getAllConversations()
    }

}