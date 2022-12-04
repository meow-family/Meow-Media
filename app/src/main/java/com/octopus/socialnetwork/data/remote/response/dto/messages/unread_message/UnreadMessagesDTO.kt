package com.octopus.socialnetwork.data.remote.response.dto.messages.unread_message


import com.google.gson.annotations.SerializedName
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageDTO
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageUserDTO

data class UnreadMessagesDTO(
    @SerializedName("list")
    val messages: List<MessageDTO>?,
    @SerializedName("withuser")
    val messageReceiver: MessageUserDTO?
)