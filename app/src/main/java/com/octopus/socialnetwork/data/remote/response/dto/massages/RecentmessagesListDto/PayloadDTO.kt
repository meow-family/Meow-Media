package com.octopus.socialnetwork.data.remote.response.dto.massages.RecentmessagesListDto


import com.google.gson.annotations.SerializedName

data class PayloadDTO(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("list")
    val list: List<MessageDetailsDTO>?,
    @SerializedName("offset")
    val offset: Int?
)