package com.octopus.socialnetwork.data.remote.response.dto.post

import com.google.gson.annotations.SerializedName
import com.octopus.socialnetwork.data.remote.response.dto.user.UserDTO

data class AllPostDTO(
    @SerializedName("count")
    val count: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("posts")
    val posts: List<PostDTO>,
    @SerializedName("viewer_is_friend")
    val viewer_is_friend: Boolean,
    @SerializedName("user")
    val user: UserDTO

)
