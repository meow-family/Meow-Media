package com.octopus.socialnetwork.data.remote.response.dto.post

import com.google.gson.annotations.SerializedName
import com.octopus.socialnetwork.data.remote.response.dto.user.UserDto
import com.octopus.socialnetwork.data.remote.response.dto.user.UserFriendsDto

data class PostDto(
    @SerializedName("post")
    val details: PostDetailsDto?,
    @SerializedName("friends")
    val friends: List<UserFriendsDto>,
    @SerializedName("text")
    val description: String?,
    @SerializedName("user")
    val user: UserDto?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("posted_user")
    val posted_user: UserDto?
)