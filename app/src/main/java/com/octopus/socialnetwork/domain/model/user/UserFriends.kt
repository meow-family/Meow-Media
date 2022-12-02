package com.octopus.socialnetwork.domain.model.user

import com.octopus.socialnetwork.domain.model.user.UserDetails

data class UserFriends(
    val total: Int,
    val friends: List<UserDetails>,
)
