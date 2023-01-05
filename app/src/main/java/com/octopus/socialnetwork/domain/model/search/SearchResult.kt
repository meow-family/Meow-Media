package com.octopus.socialnetwork.domain.model.search

import com.octopus.socialnetwork.domain.model.user.User

data class SearchResult(
    val users: List<User> = emptyList()
)
