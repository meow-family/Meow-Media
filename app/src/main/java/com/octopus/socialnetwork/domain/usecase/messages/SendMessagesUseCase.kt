package com.octopus.socialnetwork.domain.usecase.messages

import com.octopus.socialnetwork.data.local.datastore.DataStorePreferences
import com.octopus.socialnetwork.data.repository.messaging.MessagingRepository
import com.octopus.socialnetwork.domain.usecase.user.FetchUserIdUseCase
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