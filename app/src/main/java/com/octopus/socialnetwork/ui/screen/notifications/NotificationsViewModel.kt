package com.octopus.socialnetwork.ui.screen.notifications

import androidx.lifecycle.ViewModel
import com.octopus.socialnetwork.ui.screen.notifications.state.NotificationsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NotificationsViewModel: ViewModel() {
    private val _state = MutableStateFlow(NotificationsUiState())
    val state = _state.asStateFlow()

}