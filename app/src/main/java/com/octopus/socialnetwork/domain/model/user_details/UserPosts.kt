package com.octopus.socialnetwork.domain.model.user_details

import com.octopus.socialnetwork.domain.model.post.Post

data class UserPosts(
    val posts: List<Post>,
    val count: Int,
)