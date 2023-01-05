package com.octopus.socialnetwork.domain.mapper.search

import com.octopus.socialnetwork.data.remote.response.dto.search.SearchDto
import com.octopus.socialnetwork.domain.mapper.user.toUser
import com.octopus.socialnetwork.domain.model.search.SearchResult

fun SearchDto.toSearch(): SearchResult {
    return SearchResult(
        users = users?.map { it.toUser() } ?: emptyList(),
    )
}