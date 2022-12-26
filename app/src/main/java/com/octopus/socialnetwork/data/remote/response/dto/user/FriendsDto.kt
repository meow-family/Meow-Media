package com.octopus.socialnetwork.data.remote.response.dto.user

import com.google.gson.annotations.SerializedName

data class FriendsDto(
    @SerializedName("total")
    val total: Int?,
    @SerializedName("friends")
    val friends: List<UserDto>?,
)