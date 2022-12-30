package com.octopus.socialnetwork.data.remote.response.dto.comment


import com.google.gson.annotations.SerializedName

data class CommentResponse(
    @SerializedName("comments")
    val comments: List<CommentDto>?,
    @SerializedName("count")
    val count: Int?,
    @SerializedName("offset")
    val offset: Int?
)