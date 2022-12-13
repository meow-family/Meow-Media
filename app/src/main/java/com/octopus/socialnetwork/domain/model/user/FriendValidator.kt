package com.octopus.socialnetwork.domain.model.user


data class FriendValidator(
    val isFriend: Boolean,
    val requestExists: Boolean
)