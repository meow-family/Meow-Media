package com.octopus.socialnetwork.domain.model.notifications

data class NotificationItems(
    val notification: Notification,
    val postOwner: PostOwner,
    val entity: Boolean,
    val post: Boolean,
    val group: Group,
)