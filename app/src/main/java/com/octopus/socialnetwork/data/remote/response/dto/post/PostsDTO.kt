package com.octopus.socialnetwork.data.remote.response.dto.post

import com.google.gson.annotations.SerializedName
import com.octopus.socialnetwork.data.remote.response.dto.user.UserDetailsDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserFriendsDTO

data class PostDTO(

    @SerializedName("post")
    val details: PostDetailsDTO,
    @SerializedName("friends")
    val friends: List<UserFriendsDTO>,
    @SerializedName("text")
    val text: String,
    @SerializedName("user")
    val user: UserDetailsDTO,
    @SerializedName("image")
    val image: String,
    @SerializedName("posted_user")
    val posted_user: UserDetailsDTO
)
