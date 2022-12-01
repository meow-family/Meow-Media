package com.octopus.socialnetwork.domain.model.user_details

data class UserFriends(
    val total: Int,
    val friends: List<UserDetails>,
)
