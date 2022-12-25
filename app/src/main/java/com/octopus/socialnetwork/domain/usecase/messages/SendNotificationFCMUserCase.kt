package com.octopus.socialnetwork.domain.usecase.messages

import android.util.Log
import com.octopus.socialnetwork.data.remote.firebase.CloudMessagingService
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageNotificationDto
import com.octopus.socialnetwork.data.remote.response.dto.messages.NotificationData
import com.octopus.socialnetwork.domain.usecase.user.GetUserTokenUseCase
import com.octopus.socialnetwork.ui.util.extensions.getHourAndMinutes
import java.util.*
import javax.inject.Inject


class SendNotificationFCMUserCase @Inject constructor(
    private val cloudMessagingService: CloudMessagingService,
    private val getUserToken: GetUserTokenUseCase
) {
    suspend operator fun invoke(from: Int, to: Int, message: String) {
        val userToken = getUserToken(to.toString())
        Log.i("TESTING", userToken.toString() + " is the fcm token of user")
        if (userToken != null) {

            val notificationData = NotificationData(
                friendId = from, id = to, message = message,
                time = Calendar.getInstance().time.getHourAndMinutes()
            )

            cloudMessagingService.postNotification(
                notification = MessageNotificationDto(data = notificationData, to = userToken)
            )
        }

    }
}