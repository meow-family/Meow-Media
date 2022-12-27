package com.octopus.socialnetwork.domain.model.notifications

data class NotificationsCount(
    val notifications: Int,
    val messages: Int,
    val friends: Int,
)