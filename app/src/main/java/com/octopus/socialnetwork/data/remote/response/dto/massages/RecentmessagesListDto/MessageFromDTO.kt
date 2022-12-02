package com.octopus.socialnetwork.data.remote.response.dto.massages.RecentmessagesListDto


import com.google.gson.annotations.SerializedName

data class MessageFromDTO(
    @SerializedName("fullname")
    val fullName: String?,
    @SerializedName("guid")
    val guid: Int?,
    @SerializedName("icon")
    val icon: IconDTO?,
    @SerializedName("username")
    val username: String?
)