package com.octopus.socialnetwork.data.remote.response.dto.user

import com.google.gson.annotations.SerializedName

data class CheckUserFriendDto(
    @SerializedName("is_friend")
    val isFriend: Boolean?,
    @SerializedName("request_exists")
    val requestExists: Boolean?,
    @SerializedName("success")
    val success: Boolean?,
)