package com.octopus.socialnetwork.ui.screen.notifications.state

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class NotificationsUiState(
   // val notifications: List<NotificationItemsUiState> = emptyList(),
    val notifications: Flow<PagingData<NotificationItemsUiState>> = emptyFlow(),
    val isLoading: Boolean = true,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val viewed: Boolean = false,
)
