package com.octopus.socialnetwork.data.remote.response.dto.messages


import com.google.gson.annotations.SerializedName

data class MessageDTO (
    @SerializedName("answered")
    val answered: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("is_deleted_from")
    val isDeletedFrom: String?,
    @SerializedName("is_deleted_to")
    val isDeletedTo: String?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("message_from")
    val messageSender: MessageUserDTO?,
    @SerializedName("message_to")
    val messageReceiver: MessageUserDTO?,
    @SerializedName("time")
    val time: Int?,
    @SerializedName("viewed")
    val viewed: String?,
)