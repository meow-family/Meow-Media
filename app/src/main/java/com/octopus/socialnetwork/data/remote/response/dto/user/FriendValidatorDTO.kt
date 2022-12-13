package com.octopus.socialnetwork.data.remote.response.dto.user
import com.google.gson.annotations.SerializedName


data class FriendValidatorDTO(
    @SerializedName("is_friend")
    val isFriend: Boolean?,
    @SerializedName("request_exists")
    val requestExists: Boolean?
)