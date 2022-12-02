package com.octopus.socialnetwork.data.remote.response.dto.massages.list_messages


import com.google.gson.annotations.SerializedName
import com.octopus.socialnetwork.data.remote.response.dto.massages.MessageDTO
import com.octopus.socialnetwork.data.remote.response.dto.massages.MessageSenderDTO

data class MessageListDTO(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("offset")
    val offset: Boolean?,
    @SerializedName("withuser")
    val withUser: MessageSenderDTO?,
    @SerializedName("list")
    val listOfMessagesDetails: List<MessageDTO>?
)