package com.octopus.socialnetwork.domain.usecase.messages

import android.util.Log
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageNotificationDto
import com.octopus.socialnetwork.data.remote.response.dto.messages.NotificationData
import com.octopus.socialnetwork.data.remote.service.CloudMessagingService
import com.octopus.socialnetwork.domain.usecase.user.GetUserTokenUseCase
import java.util.Calendar
import javax.inject.Inject


class SendNotificationFCMUserCase @Inject constructor(
    private val cloudMessagingService: CloudMessagingService,
    private val getUserToken: GetUserTokenUseCase
) {
    suspend operator fun invoke(from: Int, to: Int, message: String) {
        val userToken = getUserToken(to.toString())
        Log.i("TESTING",userToken.toString() + " is the fcm token of user")
        if (userToken != null) {
            cloudMessagingService.postNotification(
                notification = MessageNotificationDto(
                    data = NotificationData(
                        from,
                        to,
                        message,
                        Calendar.getInstance().toString()
                    ),
                    to = userToken
                )
            )
        }

    }
}