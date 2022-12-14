package com.octopus.socialnetwork.data.remote.response.dto.search

import com.google.gson.annotations.SerializedName
import com.octopus.socialnetwork.data.remote.response.dto.notifications.GroupDto
import com.octopus.socialnetwork.data.remote.response.dto.post.PostDto
import com.octopus.socialnetwork.data.remote.response.dto.user.UserDto

data class SearchDto(
    @SerializedName("users")
    val users: List<UserDto>,
    @SerializedName("posts")
    val posts: List<PostDto>,
    @SerializedName("groups")
    val groups: List<GroupDto>,
)
