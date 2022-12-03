package com.octopus.socialnetwork.domain.model.notifications

data class UserNotificationsCount(
    val notifications: Int,
    val messages: Int,
    val friends: Int,
)