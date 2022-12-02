package com.octopus.socialnetwork.data.remote.response.dto.massages

import com.google.gson.annotations.SerializedName
import com.octopus.socialnetwork.data.remote.response.dto.auth.Avatar

data class MessageSenderDTO(
    @SerializedName("fullname")
    val fullName: String?,
    @SerializedName("guid")
    val guid: Int?,
    @SerializedName("icon")
    val avatar: Avatar?,
    @SerializedName("username")
    val username: String?
)