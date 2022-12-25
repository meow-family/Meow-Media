package com.octopus.socialnetwork.domain.usecase.messages.chat

import com.octopus.socialnetwork.data.repository.messaging.MessagingRepository
import com.octopus.socialnetwork.domain.usecase.authentication.FetchUserIdUseCase
import com.octopus.socialnetwork.domain.usecase.messages.fcm.SendNotificationFCMUserCase
import kotlinx.coroutines.flow.last
import javax.inject.Inject

class SendMessagesUseCase @Inject constructor(
    private val messagingRepository: MessagingRepository,
    private val fetchUserId: FetchUserIdUseCase,
    private val SendNotification: SendNotificationFCMUserCase,
) {
    suspend operator fun invoke(to: Int, message: String) {

        messagingRepository.sendMessage(fetchUserId(), to, message)
        SendNotification(fetchUserId(), to, message)


    }

}