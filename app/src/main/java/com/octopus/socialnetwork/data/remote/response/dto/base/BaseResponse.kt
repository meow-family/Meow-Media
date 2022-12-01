package com.octopus.socialnetwork.data.remote.response.dto.base

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("merchant")
    val merchant: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("time_token")
    val timeToken: Long?,
    @SerializedName("payload")
    val payload: T,
)
