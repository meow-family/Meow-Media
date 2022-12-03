package com.octopus.socialnetwork.data.remote.response.dto.user

import com.google.gson.annotations.SerializedName
import com.octopus.socialnetwork.data.remote.response.dto.post.PostDetailsDTO

data class UserPostsDTO(
    @SerializedName("posts")
    val posts: List<PostDetailsDTO>?,
    @SerializedName("count")
    val count: Int?,
)