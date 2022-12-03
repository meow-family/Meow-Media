package com.octopus.socialnetwork.data.remote.response.dto.auth

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("merchant")
    val merchant: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("time_token")
    val timeToken: Long?,
    @SerializedName("payloadDTO")
    val payload: Payload?,
    @SerializedName("code")
    val code: String?,
    @SerializedName("message")
    val message: String?,
)
