package com.octopus.socialnetwork.domain.model.user

data class UserFriends(
    val total: Int,
    val friends: List<UserDetails>,
)
