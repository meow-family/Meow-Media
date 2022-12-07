package com.octopus.socialnetwork.ui.screen.notifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.notifications.FetchUserNotificationsUseCase
import com.octopus.socialnetwork.ui.screen.notifications.mapper.toNotificationsUiState
import com.octopus.socialnetwork.ui.screen.notifications.state.NotificationDetailsUiState
import com.octopus.socialnetwork.ui.screen.notifications.state.NotificationsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(
    private val  fetchUserNotifications: FetchUserNotificationsUseCase,
) : ViewModel(){
    private val _state = MutableStateFlow(NotificationsUiState())
    val state = _state.asStateFlow()

    init {
        getNotifications(16)
    }

    private fun getNotifications(userId: Int) {
        try {
            viewModelScope.launch {
                val userNotifications = fetchUserNotifications(userId, null, null).toNotificationsUiState().notifications

                _state.update {
                    it.copy(
                        notifications = userNotifications,
                        isLoading = false,
                        isError = false,
                    )
                }

            }
        } catch (e: Exception) {
            _state.update {
                it.copy(
                    isLoading = false,
                    isError = true
                )
            }
        }
    }
}