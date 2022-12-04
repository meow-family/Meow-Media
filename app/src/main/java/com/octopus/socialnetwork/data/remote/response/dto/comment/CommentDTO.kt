package com.octopus.socialnetwork.data.remote.response.dto.comment


import com.google.gson.annotations.SerializedName

data class CommentDTO(
    @SerializedName("comments")
    val comments: List<CommentDetails>,
    @SerializedName("count")
    val count: Int,
    @SerializedName("offset")
    val offset: Int
)