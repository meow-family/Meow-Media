package com.octopus.socialnetwork.domain.model.notifications

data class UserNotifications(
    val list: List<NotificationItems>,
    val count: Int,
    val offset: Int,
)