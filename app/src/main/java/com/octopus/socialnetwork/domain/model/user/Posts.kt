package com.octopus.socialnetwork.domain.model.user

import com.octopus.socialnetwork.domain.model.post.Post

data class Posts(
    val posts: List<Post>,
    val count: Int,
)