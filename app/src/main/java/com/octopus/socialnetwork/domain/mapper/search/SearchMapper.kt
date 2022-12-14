package com.octopus.socialnetwork.domain.mapper.search

import com.octopus.socialnetwork.data.remote.response.dto.search.SearchDto
import com.octopus.socialnetwork.domain.mapper.notifications.toGroup
import com.octopus.socialnetwork.domain.mapper.posts.toPost
import com.octopus.socialnetwork.domain.mapper.user.toUserDetails
import com.octopus.socialnetwork.domain.model.search.Search

fun SearchDto.toSearch(): Search {
    return Search(
        users = users.map { it.toUserDetails() },
        posts = posts.map { it.toPost() },
        groups = groups.map { it.toGroup() },
    )
}