package com.octopus.socialnetwork.domain.model.search

import com.octopus.socialnetwork.domain.model.user.User

data class Search(
    val searchResults: List<User>
)
