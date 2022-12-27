package com.octopus.socialnetwork.domain.model.notifications

import java.util.*

data class Notification(
    val notificationId: Int,
    val type: String,
    val notificationUserId: Int,
    val subjectOwnerId: Int,
    val subjectId: Int,
    val viewed: Boolean,
    val timeCreated: Date,
    val itemId: Int,
)