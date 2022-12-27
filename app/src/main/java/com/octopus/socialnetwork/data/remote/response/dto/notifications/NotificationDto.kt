package com.octopus.socialnetwork.data.remote.response.dto.notifications

import com.google.gson.annotations.SerializedName

data class NotificationDto(
    @SerializedName("guid")
    val notificationId: Int?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("poster_guid")
    val notificationUserId: Int?,
    @SerializedName("owner_guid")
    val subjectOwnerId: Int?,
    @SerializedName("subject_guid")
    val subjectId: Int?,
    @SerializedName("viewed")
    val viewed: String? = null,
    @SerializedName("time_created")
    val timeCreated: Long?,
    @SerializedName("item_guid")
    val itemGuid: Int?,
)