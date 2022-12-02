package com.octopus.socialnetwork.data.remote.response.dto.massages.RecentmessagesListDto


import com.google.gson.annotations.SerializedName

data class MessageDetailsDTO (
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
    val messageFrom: MessageFromDTO?,
    @SerializedName("message_to")
    val messageTo: MessageToDTO?,
    @SerializedName("time")
    val time: Int?,
    @SerializedName("viewed")
    val viewed: String?,
)