package com.octopus.socialnetwork.data.remote.response.dto.group.others

import com.google.gson.annotations.SerializedName

data class Icon(
    @SerializedName("large")
    val large: String,
    @SerializedName("larger")
    val larger: String,
    @SerializedName("small")
    val small: String,
    @SerializedName("smaller")
    val smaller: String,
    @SerializedName("topbar")
    val topBar: String
)