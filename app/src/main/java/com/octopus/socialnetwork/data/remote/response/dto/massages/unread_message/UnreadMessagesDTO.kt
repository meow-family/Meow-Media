package com.octopus.socialnetwork.data.remote.response.dto.massages.unread_message


import com.google.gson.annotations.SerializedName
import com.octopus.socialnetwork.data.remote.response.dto.massages.MessageSenderDTO

data class UnreadMessagesDTO(
    @SerializedName("list")
    val ifUnread: Boolean?,
    @SerializedName("withuser")
    val withUser: MessageSenderDTO?
)