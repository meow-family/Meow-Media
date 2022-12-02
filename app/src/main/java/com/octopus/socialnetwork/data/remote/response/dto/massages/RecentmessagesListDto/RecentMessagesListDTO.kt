package com.octopus.socialnetwork.data.remote.response.dto.massages.RecentmessagesListDto

import com.google.gson.annotations.SerializedName

data class RecentMessagesListDTO(
    @SerializedName("code")
    val code: String?,
    @SerializedName("merchant")
    val merchant: String?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("payloadDTO")
    val payloadDTO: PayloadDTO?,
    @SerializedName("time_token")
    val timeToken: Int?,
    @SerializedName("url")
    val url: String?
)