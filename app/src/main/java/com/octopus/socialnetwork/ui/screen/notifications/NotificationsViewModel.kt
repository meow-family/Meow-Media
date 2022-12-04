package com.octopus.socialnetwork.ui.screen.notifications

import androidx.lifecycle.ViewModel
import com.octopus.socialnetwork.ui.screen.notifications.state.NotificationDetailsUiState
import com.octopus.socialnetwork.ui.screen.notifications.state.NotificationsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NotificationsViewModel: ViewModel() {
    private val _state = MutableStateFlow(NotificationsUiState())
    val state = _state.asStateFlow()

    init {
        getNotifi()
    }

    fun getNotifi() {
        _state.update {
            it.copy(
                notifications = listOf(
                    NotificationDetailsUiState(

                    )
                )
            )
        }
    }
}