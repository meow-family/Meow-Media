package com.octopus.socialnetwork.data.remote.response.dto.post

import com.google.gson.annotations.SerializedName

data class PostDTO(
    @SerializedName("guid")
    val guid: Int?,
    @SerializedName("owner_guid")
    val ownerGuid: Int?,
    @SerializedName("title")
    val title: String?,
)
