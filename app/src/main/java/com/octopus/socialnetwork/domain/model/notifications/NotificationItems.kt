package com.octopus.socialnetwork.domain.model.notifications

data class NotificationItems(
    val notification: Notification,
    val poster: Poster,
    val entity: Boolean,
    val post: Boolean,
    val group: Group,
)