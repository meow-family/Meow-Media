package com.octopus.socialnetwork.domain.model.notifications

data class NotificationItem(
    val notification: Notification,
    val postOwner: PostOwner,
    val entity: Boolean,
    val post: Boolean,
)