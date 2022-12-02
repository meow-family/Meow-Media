package com.octopus.socialnetwork.domain.model.user


data class CheckUserFriend(
    val isFriend: Boolean,
    val requestExists: Boolean
)