package com.octopus.socialnetwork.domain.usecase.messages

import android.util.Log
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageDto
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageNotificationDto
import com.octopus.socialnetwork.data.remote.response.dto.messages.NotificationData
import com.octopus.socialnetwork.data.remote.service.CloudMessagingService
import com.octopus.socialnetwork.data.repository.messaging.ChatFirebaseDataSource
import com.octopus.socialnetwork.data.repository.messaging.MessagingRepository
import com.octopus.socialnetwork.domain.mapper.messages.toMessageDetails
import com.octopus.socialnetwork.domain.model.messages.MessageDetails
import com.octopus.socialnetwork.domain.usecase.user.FetchUserIdUseCase
import java.util.Calendar
import javax.inject.Inject

class SendMessagesUseCase @Inject constructor(
    private val socialRepository: MessagingRepository,
    private val fetchUserId: FetchUserIdUseCase,
    private val cloudMessagingService: CloudMessagingService,
) {
    suspend operator fun invoke( to: Int, message: String): List<MessageDetails> {
        val result = cloudMessagingService.postNotification(notification = MessageNotificationDto(data = NotificationData(fetchUserId(),to,message,Calendar.getInstance().toString()),
            to = "doapEwC8SDyd6zkwl2ZWNF:APA91bHeMCbiZnK1tM5QX83KZzefBE7bdd6gaR-lRHsIKF5xsfnaHgjFZKEvLG8EgIscmEjeXv3vrT3Ru6TalvOQDLRSjw5AQa82nGS4S56pP_Jn1rpMEm1C8RG5YC6wCrawjcyRF4wq"))
            socialRepository.getMessages(currentUserId = fetchUserId(), otherUserId = to).messages

        Log.i("TESTING","result of push notification $result , requestBody is ${result.body()} ")
        return if (result.isSuccessful) {
            socialRepository.sendMessage(fetchUserId(), to, message).toMessageDetails(fetchUserId())
             socialRepository.getMessages(currentUserId = fetchUserId(), otherUserId = to).messages?.map { it.toMessageDetails(userId = fetchUserId()) } ?: emptyList()
        } else {
            emptyList()
        }
    }
}