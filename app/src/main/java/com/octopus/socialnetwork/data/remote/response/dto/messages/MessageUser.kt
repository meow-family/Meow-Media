package com.octopus.socialnetwork.data.remote.response.dto.messages

import com.google.gson.annotations.SerializedName
import com.octopus.socialnetwork.data.remote.response.dto.auth.Avatar

data class MessageUser(
    @SerializedName("fullname")
    val fullName: String?,
    @SerializedName("guid")
    val userId: Int?,
    @SerializedName("icon")
    val avatar: Avatar?,
    @SerializedName("username")
    val username: String?
)