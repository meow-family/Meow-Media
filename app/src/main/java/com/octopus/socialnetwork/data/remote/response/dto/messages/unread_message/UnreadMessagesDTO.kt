package com.octopus.socialnetwork.data.remote.response.dto.messages.unread_message


import com.google.gson.annotations.SerializedName
import com.octopus.socialnetwork.data.remote.response.dto.messages.Message
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageUser

data class UnreadMessagesDTO(
    @SerializedName("list")
    val messages: List<Message>?,
    @SerializedName("withuser")
    val messageReceiver: MessageUser?
)