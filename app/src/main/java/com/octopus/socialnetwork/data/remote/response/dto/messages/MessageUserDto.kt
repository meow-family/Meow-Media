package com.octopus.socialnetwork.data.remote.response.dto.messages

import com.google.gson.annotations.SerializedName
import com.octopus.socialnetwork.data.remote.response.dto.Avatar

data class MessageUserDto(
    @SerializedName("fullname")
    val fullName: String?,
    @SerializedName("guid")
    val userId: Int?,
    @SerializedName("icon")
    val avatar: Avatar?,
    @SerializedName("username")
    val username: String?
)