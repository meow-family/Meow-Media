package com.octopus.socialnetwork.domain.model.notifications

data class Notification(
    val guid: Int,
    val type: String,
    val posterGuid: Int,
    val ownerGuid: Int,
    val subjectGuid: Int,
    val viewed: String,
    val timeCreated: Long,
    val itemGuid: Int,
)