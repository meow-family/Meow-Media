package com.octopus.socialnetwork.data.remote.response.dto.user.friend_requests


import com.google.gson.annotations.SerializedName
import com.octopus.socialnetwork.data.remote.response.dto.user.UserDto

data class FriendRequestsListDTO(
    @SerializedName("count")
    val count: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("requests")
    val requests: List<UserDto>
)