package com.octopus.socialnetwork.data.remote.response.dto.massages.message_send

import com.google.gson.annotations.SerializedName
import com.octopus.socialnetwork.data.remote.response.dto.massages.MessageSenderDTO

data class SendMessageDTO(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("message_from")
    val messageFrom: MessageSenderDTO?,
    @SerializedName("message_to")
    val messageTo: MessageSenderDTO?,
    @SerializedName("time")
    val time: Int?,
    @SerializedName("viewed")
    val viewed: String?
)