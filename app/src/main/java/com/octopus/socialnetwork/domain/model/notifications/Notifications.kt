package com.octopus.socialnetwork.domain.model.notifications

data class Notifications(
    val notifications: List<NotificationItem>,
    val count: Int,
    val offset: Int,
)