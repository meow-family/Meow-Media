package com.octopus.socialnetwork.domain.model.search

import com.octopus.socialnetwork.domain.model.notifications.Group
import com.octopus.socialnetwork.domain.model.post.Post
import com.octopus.socialnetwork.domain.model.user.UserDetails

data class Search(
    val users: List<UserDetails>,
    val posts: List<Post>,
    val groups: List<Group>,
)
