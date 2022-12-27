package com.octopus.socialnetwork.domain.usecase.messages.fcm

import com.octopus.socialnetwork.data.remote.response.dto.messages.NotificationData
import com.octopus.socialnetwork.data.repository.messaging.MessagingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReceiveMessageUseCase @Inject constructor(
    private val messagingRepository: MessagingRepository,
) {
    operator fun invoke(): Flow<NotificationData>  {
        return messagingRepository.onReceiveNotification()
    }
}