package com.octopus.socialnetwork.domain.model.user

data class UserFriendChecker(
    val isFriend: Boolean,
    val requestExists: Boolean,
    val success: Boolean,
)