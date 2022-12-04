package com.octopus.socialnetwork.data.remote.response.dto.notifications

import com.google.gson.annotations.SerializedName

data class UserNotificationsCountDTO(
    @SerializedName("notifications")
    val notifications: Int?,
    @SerializedName("messages")
    val messages: Int?,
    @SerializedName("friends")
    val friends: Int?,
)