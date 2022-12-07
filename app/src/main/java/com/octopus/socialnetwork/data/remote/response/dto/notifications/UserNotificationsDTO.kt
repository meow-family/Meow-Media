package com.octopus.socialnetwork.data.remote.response.dto.notifications

import com.google.gson.annotations.SerializedName

data class UserNotificationsDTO(
    @SerializedName("list")
    val list: List<NotificationItemsDto>?,
    @SerializedName("count")
    val count: Int?,
    @SerializedName("offset")
    val offset: Int?,
)