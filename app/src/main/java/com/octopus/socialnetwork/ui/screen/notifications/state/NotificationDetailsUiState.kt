package com.octopus.socialnetwork.ui.screen.notifications.state

import java.util.*

data class NotificationItemsUiState(
    val notificationDetails: NotificationDetailsUiState = NotificationDetailsUiState(),
    val posterDetails: NotificationPosterUiState = NotificationPosterUiState()
)

data class NotificationDetailsUiState(
    val id: Int = 0,
    val type: String = "",
    val posterId: Int = 0,
    val ownerId: Int = 0,
    val subjectId: Int = 0,
    val viewed: Boolean = false,
    val timeCreated: Date = Date(),
    val itemId: Int = 0,
)

data class NotificationPosterUiState(
    val posterId: Int = 0,
    val posterFullName: String = "",
    val posterAvatar: String = "",
)