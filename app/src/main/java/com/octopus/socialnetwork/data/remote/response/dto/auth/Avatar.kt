package com.octopus.socialnetwork.data.remote.response.dto.auth

import com.google.gson.annotations.SerializedName

data class Avatar(
    @SerializedName("topbar")
    val varySmall: String?,
    @SerializedName("small")
    val small: String?,
    @SerializedName("smaller")
    val smaller: String?,
    @SerializedName("larger")
    val larger: String?,
    @SerializedName("large")
    val large: String?,
)
