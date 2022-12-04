package com.octopus.socialnetwork.domain.model.notifications

data class UserNotifications(
    val notifications: List<NotificationItems>,
    val count: Int,
    val offset: Int,
)