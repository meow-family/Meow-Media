package com.octopus.socialnetwork.data.remote.response.dto.user

import com.google.gson.annotations.SerializedName
import com.octopus.socialnetwork.data.remote.response.dto.post.PostDTO

data class UserPostsDTO(
    @SerializedName("posts")
    val posts: List<PostDTO>?,
    @SerializedName("count")
    val count: Int?,
)