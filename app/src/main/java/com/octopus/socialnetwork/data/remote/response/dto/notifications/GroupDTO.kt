package com.octopus.socialnetwork.data.remote.response.dto.notifications

import com.google.gson.annotations.SerializedName

data class GroupDTO(
    @SerializedName("guid")
    val guid: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("ismember")
    val isMember: Boolean?,
)