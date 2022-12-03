package com.octopus.socialnetwork.data.remote.response.dto.notifications

import com.google.gson.annotations.SerializedName

data class NotificationItemsDTO(
    @SerializedName("notification")
    val notification: NotificationDTO?,
    @SerializedName("poster")
    val poster: PosterDTO?,
    @SerializedName("entity")
    val entity: Boolean?,
    @SerializedName("post")
    val post: Boolean?,
    @SerializedName("group")
    val group: GroupDTO?,
)