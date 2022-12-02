package com.octopus.socialnetwork.data.remote.response.dto.massages.recent_messages


import com.google.gson.annotations.SerializedName
import com.octopus.socialnetwork.data.remote.response.dto.massages.MessageDTO

data class RecentMessagesDTO(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("list")
    val list: List<MessageDTO>?,
)