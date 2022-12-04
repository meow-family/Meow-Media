package com.octopus.socialnetwork.data.remote.response.dto.notifications

import com.google.gson.annotations.SerializedName

data class NotificationDTO(
    @SerializedName("guid")
    val guid: Int?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("poster_guid")
    val posterGuid: Int?,
    @SerializedName("owner_guid")
    val ownerGuid: Int?,
    @SerializedName("subject_guid")
    val subjectGuid: Int?,
    @SerializedName("viewed")
    val viewed: String? = null,
    @SerializedName("time_created")
    val timeCreated: Long?,
    @SerializedName("item_guid")
    val itemGuid: Int?,
)