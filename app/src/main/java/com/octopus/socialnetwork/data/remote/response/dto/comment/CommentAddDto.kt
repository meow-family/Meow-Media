package com.octopus.socialnetwork.data.remote.response.dto.comment


import com.google.gson.annotations.SerializedName
import com.octopus.socialnetwork.data.remote.response.dto.user.UserDto

data class CommentAddDto(
    @SerializedName("comment")
    val comment: CommentDto?,
    @SerializedName("user")
    val user: UserDto?
)