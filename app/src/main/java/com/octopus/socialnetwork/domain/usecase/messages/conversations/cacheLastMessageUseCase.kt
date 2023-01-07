package com.octopus.socialnetwork.domain.usecase.messages.conversations

import com.octopus.socialnetwork.data.remote.response.dto.messages.NotificationData
import com.octopus.socialnetwork.data.repository.messaging.MessagingRepository
import javax.inject.Inject

class cacheLastMessageUseCase @Inject constructor(
    private val messagingRepository: MessagingRepository
) {
    suspend operator fun invoke(message: NotificationData) {
        messagingRepository.updateConversation(
            message = message.message,
            time = message.time,
            friendId = message.friendId
        )
    }

}