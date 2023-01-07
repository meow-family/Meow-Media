package com.octopus.socialnetwork.domain.usecase.messages.fcm

import android.util.Log
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageNotificationDto
import com.octopus.socialnetwork.data.remote.response.dto.messages.NotificationData
import com.octopus.socialnetwork.data.remote.service.fcm.CloudMessagingService
import com.octopus.socialnetwork.data.repository.authentication.AuthenticationRepository
import com.octopus.socialnetwork.domain.utils.getHourAndMinutes
import java.util.*
import javax.inject.Inject


class SendNotificationFCMUserCase @Inject constructor(
    private val cloudMessagingService: CloudMessagingService,
    private val authenticationRepository: AuthenticationRepository,
) {
    suspend operator fun invoke(from: Int, to: Int, message: String) {
        val userToken = authenticationRepository.getFirebaseUser(to.toString())?.token
        Log.i("MEOW", userToken.toString() + " is the fcm token of user")
        if (userToken != null) {

            val notificationData = NotificationData(
                friendId = from, id = to, message = message,
                time = Calendar.getInstance().time.time.toString()
            )

            cloudMessagingService.postNotification(
                notification = MessageNotificationDto(data = notificationData, to = userToken)
            )
        }

    }
}