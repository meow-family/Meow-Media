package com.octopus.socialnetwork.ui.screen.notifications.state

data class NotificationsUiState(
    val notifications: List<NotificationDetailsUiState> = emptyList(),
    val isLoading: Boolean = true,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
)
