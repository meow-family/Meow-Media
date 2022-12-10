package com.octopus.socialnetwork.ui.screen.notifications.state

data class NotificationsUiState(
    val notifications: List<NotificationItemsUiState> = emptyList(),
    val isLoading: Boolean = true,
    val isError: Boolean = false,
)
