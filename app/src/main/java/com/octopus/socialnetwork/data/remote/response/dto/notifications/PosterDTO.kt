package com.octopus.socialnetwork.data.remote.response.dto.notifications

import com.google.gson.annotations.SerializedName

data class PosterDTO(
    @SerializedName("guid")
    val guid: Int?,
    @SerializedName("fullname")
    val fullName: String?,
    @SerializedName("icon")
    val icon: String?,
)