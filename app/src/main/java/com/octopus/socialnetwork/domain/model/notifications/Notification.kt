package com.octopus.socialnetwork.domain.model.notifications

import java.util.*

data class Notification(
    val guid: Int,
    val type: String,
    val posterId: Int,
    val ownerId: Int,
    val subjectId: Int,
    val viewed: Boolean,
    val timeCreated: Date,
    val itemId: Int,
)