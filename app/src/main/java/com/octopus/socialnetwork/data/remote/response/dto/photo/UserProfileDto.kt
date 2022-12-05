package com.octopus.socialnetwork.data.remote.response.dto.photo

import com.google.gson.annotations.SerializedName
import com.octopus.socialnetwork.data.remote.response.dto.user.UserDto

data class UserProfileDto(
    @SerializedName("photo")
    val photo: Photo,
    @SerializedName("user")
    val user: UserDto
)