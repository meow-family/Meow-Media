package com.octopus.socialnetwork.ui.screen.notifications.state

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
    val viewed: String = "",
    val timeCreated: Long = 0,
    val itemId: Int = 0,
)

data class NotificationPosterUiState(
    val posterId: Int = 0,
    val posterFullName: String = "",
    val posterAvatar: String = "",
)