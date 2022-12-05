package com.octopus.socialnetwork.data.remote.response.dto.messages

import com.google.gson.annotations.SerializedName

data class RecentMessagesDto(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("list")
    val messages: List<MessageDto>?,
)