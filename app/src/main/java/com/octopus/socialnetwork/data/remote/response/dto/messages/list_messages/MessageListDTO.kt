package com.octopus.socialnetwork.data.remote.response.dto.messages.list_messages


import com.google.gson.annotations.SerializedName
import com.octopus.socialnetwork.data.remote.response.dto.messages.Message
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageUser

data class MessageListDTO(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("offset")
    val offset: Boolean?,
    @SerializedName("withuser")
    val messageReceiver: MessageUser?,
    @SerializedName("list")
    val messages: List<Message>?
)