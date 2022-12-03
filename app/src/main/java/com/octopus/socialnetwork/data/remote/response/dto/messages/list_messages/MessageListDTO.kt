package com.octopus.socialnetwork.data.remote.response.dto.messages.list_messages


import com.google.gson.annotations.SerializedName
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageDTO
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageUserDTO

data class MessageListDTO(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("offset")
    val offset: Boolean?,
    @SerializedName("withuser")
    val messageReceiver: MessageUserDTO?,
    @SerializedName("list")
    val messages: List<MessageDTO>?
)