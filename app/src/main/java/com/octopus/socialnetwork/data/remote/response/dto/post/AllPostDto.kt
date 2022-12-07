package com.octopus.socialnetwork.data.remote.response.dto.post

import com.google.gson.annotations.SerializedName
import com.octopus.socialnetwork.data.remote.response.dto.user.UserDto

data class AllPostDto(
    @SerializedName("count")
    val count: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("posts")
    val posts: List<PostDto>,
    @SerializedName("viewer_is_friend")
    val viewer_is_friend: Boolean,
    @SerializedName("user")
    val user: UserDto

)
