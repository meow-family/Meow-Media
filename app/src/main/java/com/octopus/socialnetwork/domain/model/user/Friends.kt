package com.octopus.socialnetwork.domain.model.user

data class Friends(
    val total: Int,
    val friends: List<User>,
)
