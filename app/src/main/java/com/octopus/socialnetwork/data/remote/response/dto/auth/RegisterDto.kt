package com.octopus.socialnetwork.data.remote.response.dto.auth

import com.google.gson.annotations.SerializedName

data class RegisterDto(
    @SerializedName("fullname")
    val fullname: String?,
    @SerializedName("guid")
    val userId: Int?,
    @SerializedName("icon")
    val icon: AvatarSmall?,
    @SerializedName("username")
    val username: String?
)

data class AvatarSmall(
    @SerializedName("small")
    val small: String?
)