package com.octopus.socialnetwork.data.remote.response.dto.messages

import com.google.gson.annotations.SerializedName

data class SendMessageDto(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("message_from")
    val messageSender: MessageUserDto?,
    @SerializedName("message_to")
    val messageReceiver: MessageUserDto?,
    @SerializedName("time")
    val time: Long?,
    @SerializedName("viewed")
    val viewed: String?
)