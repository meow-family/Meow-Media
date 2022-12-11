package com.octopus.socialnetwork.data.remote.response.dto.messages


import com.google.gson.annotations.SerializedName

data class MessageListDto(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("withuser")
    val messageReceiver: MessageUserDto?,
    @SerializedName("list")
    val messages: List<MessageDto>?
)