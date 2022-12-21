package com.octopus.socialnetwork.ui.screen.notifications.state

data class NotificationsUiState(
    val notifications: List<NotificationItemsUiState> = emptyList(),
    val isLoading: Boolean = true,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val viewed: Boolean = false,
)
