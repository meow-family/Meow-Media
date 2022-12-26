package com.octopus.socialnetwork.data.remote.response.dto.auth

import com.google.gson.annotations.SerializedName
import com.octopus.socialnetwork.data.remote.response.dto.Avatar


data class RegisterDto(
    @SerializedName("fullname")
    val fullname: String?,
    @SerializedName("guid")
    val userId: Int?,
    @SerializedName("icon")
    val icon: Avatar?,
    @SerializedName("username")
    val username: String?
)