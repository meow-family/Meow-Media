package com.octopus.socialnetwork.domain.model.search

import com.octopus.socialnetwork.domain.model.user.UserDetails

data class Search(
    val users: List<UserDetails>
)
