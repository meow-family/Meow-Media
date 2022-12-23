package com.octopus.socialnetwork.domain.usecase.messages

import android.util.Log
import com.octopus.socialnetwork.data.local.datastore.DataStorePreferences
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageNotificationDto
import com.octopus.socialnetwork.data.remote.response.dto.messages.NotificationData
import com.octopus.socialnetwork.data.remote.service.CloudMessagingService
import com.octopus.socialnetwork.data.repository.messaging.MessagingRepository
import com.octopus.socialnetwork.domain.mapper.messages.toMessageDetails
import com.octopus.socialnetwork.domain.model.messages.MessageDetails
import com.octopus.socialnetwork.domain.usecase.user.FetchUserIdUseCase
import java.util.*
import javax.inject.Inject

class SendMessagesUseCase @Inject constructor(
    private val socialRepository: MessagingRepository,
    private val fetchUserId: FetchUserIdUseCase,
    private val cloudMessagingService: CloudMessagingService,
    private val dataStorePreferences: DataStorePreferences /*from test only*/
) {
    suspend operator fun invoke(to: Int, message: String): MessageDetails {

        return socialRepository.sendMessage(fetchUserId(), to, message)
            .toMessageDetails(fetchUserId()).also {
                val token = dataStorePreferences.readFcmToken("FCM_TOKEN")
                token.collect {
                    Log.i("TESTING", "datastore token $it")

                    val result = cloudMessagingService.postNotification(
                        notification = MessageNotificationDto(
                            data = NotificationData(
                                fetchUserId(),
                                to,
                                message,
                                Calendar.getInstance().toString()
                            ),
                            to = "$it"
                        )
                    )
                    socialRepository.getMessages(
                        currentUserId = fetchUserId(),
                        otherUserId = to
                    ).messages
                    Log.i(
                        "TESTING",
                        "result of push notification $result , requestBody is ${result.body()} "
                    )
                }
            }

    }
}