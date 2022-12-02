package com.octopus.socialnetwork.data.remote.response.dto.album


import com.google.gson.annotations.SerializedName

data class AlbumResponse(
    @SerializedName("code")
    val code: String?,
    @SerializedName("merchant")
    val merchant: String?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("payload")
    val payload: Boolean?,
    @SerializedName("time_token")
    val timeToken: Int?,
    @SerializedName("url")
    val url: String?
)