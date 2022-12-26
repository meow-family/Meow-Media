package com.octopus.socialnetwork.data.remote.response.dto.user.friend_requests

import com.google.gson.annotations.SerializedName

data class FriendValidatorResponse(
    @SerializedName("is_friend")
    val isFriend: Boolean?,
    @SerializedName("request_exists")
    val requestExists: Boolean?,
    @SerializedName("success")
    val success: Boolean?,
)