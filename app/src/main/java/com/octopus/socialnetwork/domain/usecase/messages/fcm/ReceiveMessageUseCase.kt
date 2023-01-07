package com.octopus.socialnetwork.domain.usecase.messages.fcm

import com.octopus.socialnetwork.data.remote.response.dto.messages.NotificationData
import com.octopus.socialnetwork.data.repository.messaging.MessagingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.util.*
import javax.inject.Inject

class ReceiveMessageUseCase @Inject constructor(
    private val messagingRepository: MessagingRepository,
) {
    operator fun invoke(): Flow<NotificationData>  {
        return messagingRepository.onReceiveNotification().onEach { message ->
            messagingRepository.updateConversation(
                message = message.message,
                time = message.time,
                friendId = message.friendId
            )
        }
    }
}