package com.octopus.socialnetwork.data.remote.response.dto.messages

import com.google.gson.annotations.SerializedName
import java.util.Calendar
import java.util.Date

object NotificationKeys{
    const val ID_KEY = "id"
    const val FRIEND_ID_KEY = "friendId"
    const val MESSAGE_KEY = "messageText"
    const val TIME_KEY = "time"

}

data class MessageNotificationDto(
    val data: NotificationData = NotificationData(),
    val to: String = ""
)

data class NotificationData(
    @SerializedName(NotificationKeys.ID_KEY)
    val id: Int = 0,
    @SerializedName(NotificationKeys.FRIEND_ID_KEY)
    val friendId: Int = 0,
    @SerializedName(NotificationKeys.MESSAGE_KEY)
    val message: String = "",
    @SerializedName(NotificationKeys.TIME_KEY)
    val time: String = "",
    )