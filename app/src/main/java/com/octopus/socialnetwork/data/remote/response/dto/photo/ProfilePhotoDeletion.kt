package com.octopus.socialnetwork.data.remote.response.dto.photo

import com.google.gson.annotations.SerializedName
import com.octopus.socialnetwork.data.remote.response.dto.user.UserDto

data class ProfilePhotoDeletion(
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("user")
    val user: UserDto
)