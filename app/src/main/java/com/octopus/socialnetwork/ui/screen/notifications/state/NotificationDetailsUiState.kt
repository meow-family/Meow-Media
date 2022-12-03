package com.octopus.socialnetwork.ui.screen.notifications.state

data class NotificationDetailsUiState(
    val guid: Int = 0,
    val type: String = "",
    val posterId: Int = 0,
    val ownerId: Int = 0,
    val subjectId: Int = 0,
    val viewed: String = "",
    val timeCreated: Long = 0,
    val itemId: Int = 0,
)
