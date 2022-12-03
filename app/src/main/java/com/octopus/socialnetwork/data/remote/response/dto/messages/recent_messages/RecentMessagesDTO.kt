package com.octopus.socialnetwork.data.remote.response.dto.messages.recent_messages


import com.google.gson.annotations.SerializedName
import com.octopus.socialnetwork.data.remote.response.dto.messages.Message

data class RecentMessagesDTO(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("list")
    val messages: List<Message>?,
)