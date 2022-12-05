package com.octopus.socialnetwork.data.remote.response.dto.messages

import com.google.gson.annotations.SerializedName

data class UnreadMessagesDto(
    @SerializedName("list")
    val messages: List<MessageDto>?,
    @SerializedName("withuser")
    val messageReceiver: MessageUserDto?
)