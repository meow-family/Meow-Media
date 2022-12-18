package com.octopus.socialnetwork.data.remote.response.dto.search

import com.google.gson.annotations.SerializedName
import com.octopus.socialnetwork.data.remote.response.dto.user.UserDto

data class SearchDto(
    @SerializedName("users")
    val users: List<UserDto>?,
)
