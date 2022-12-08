package com.octopus.socialnetwork.data.remote.response.base

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("code")
    @Expose
    val code: String?,
    @SerializedName("merchant")
    @Expose
    val merchant: String?,
    @SerializedName("message")
    @Expose
    val message: String?,
    @SerializedName("time_token")
    @Expose
    val timeToken: Int?,
    @SerializedName("url")
    @Expose
    val url: String?,
    @SerializedName("payload")
    @Expose(deserialize = false)
    val result: T,
)