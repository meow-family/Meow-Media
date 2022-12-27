package com.octopus.socialnetwork.data.remote.response.base

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("code")
    val code: String?,
    @SerializedName("merchant")
    val merchant: String?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("time_token")
    val timeToken: Int?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("payload")
    val result: T,
)