package com.octopus.socialnetwork.data.remote.response.dto.user

import com.google.gson.annotations.SerializedName
import com.octopus.socialnetwork.data.remote.response.dto.post.PostDto

data class PostsDto(
    @SerializedName("posts")
    val posts: List<PostDto>?,
    @SerializedName("count")
    val count: Int?,
    @SerializedName("offset")
    val offset: Int,
)