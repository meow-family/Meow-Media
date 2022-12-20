package com.octopus.socialnetwork.ui.screen.notifications.state

import java.util.*

data class NotificationItemsUiState(
    val id: Int = 0,
    val type: String = "",
    val ownerId: Int = 0,
    val subjectId: Int = 0,
    val viewed: Boolean = false,
    val timeCreated: Date = Date(),
    val itemId: Int = 0,
    val posterId: Int = 0,
    val posterFullName: String = "",
    val posterAvatar: String = "",
)
