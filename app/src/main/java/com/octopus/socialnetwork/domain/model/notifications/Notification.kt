package com.octopus.socialnetwork.domain.model.notifications

data class Notification(
    val guid: Int,
    val type: String,
    val posterId: Int,
    val ownerId: Int,
    val subjectId: Int,
    val viewed: String,
    val timeCreated: Long,
    val itemId: Int,
)