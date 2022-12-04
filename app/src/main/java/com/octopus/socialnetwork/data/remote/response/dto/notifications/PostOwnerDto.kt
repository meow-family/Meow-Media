package com.octopus.socialnetwork.data.remote.response.dto.notifications

import com.google.gson.annotations.SerializedName

data class PostOwnerDto(
    @SerializedName("guid")
    val userId: Int?,
    @SerializedName("fullname")
    val fullName: String?,
    @SerializedName("icon")
    val icon: String?,
)