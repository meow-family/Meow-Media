package com.octopus.socialnetwork.ui.screen.friend_request.state

import com.octopus.socialnetwork.ui.screen.notifications.state.NotificationItemsUiState


data class FriendRequestUiState(
    val friendRequests: List<NotificationItemsUiState> = emptyList(),
    val isLoading: Boolean = true,
    val isError: Boolean = false,
)