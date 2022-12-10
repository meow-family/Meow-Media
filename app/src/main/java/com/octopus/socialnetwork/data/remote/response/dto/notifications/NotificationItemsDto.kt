package com.octopus.socialnetwork.data.remote.response.dto.notifications

import com.google.gson.annotations.SerializedName

data class NotificationItemsDto(
    @SerializedName("notification")
    val notification: NotificationDto?,
    @SerializedName("poster")
    val postOwner: PostOwnerDto?,
    @SerializedName("entity")
    val entity: Boolean?,
    @SerializedName("post")
    val post: Boolean?,
    @SerializedName("group")
    val group: GroupDto?,
)