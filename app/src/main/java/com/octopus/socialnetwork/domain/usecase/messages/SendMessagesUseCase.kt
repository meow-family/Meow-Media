package com.octopus.socialnetwork.domain.usecase.messages

import android.util.Log
import com.octopus.socialnetwork.data.local.datastore.DataStorePreferences
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageNotificationDto
import com.octopus.socialnetwork.data.remote.response.dto.messages.NotificationData
import com.octopus.socialnetwork.data.remote.service.CloudMessagingService
import com.octopus.socialnetwork.data.repository.messaging.MessagingRepository
import com.octopus.socialnetwork.domain.usecase.user.FetchUserIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import java.util.*
import javax.inject.Inject

class SendMessagesUseCase @Inject constructor(
    private val messagingRepository: MessagingRepository,
    private val fetchUserId: FetchUserIdUseCase,
    private val SendNotification: SendNotificationFCMUserCase,

    private val dataStorePreferences: DataStorePreferences /*from test only*/
) {
    suspend operator fun invoke(to: Int, message: String) {

        messagingRepository.sendMessage(fetchUserId(), to, message)
        SendNotification(fetchUserId(), to , message)



    }

}