package com.octopus.socialnetwork.ui.screen.notifications.state

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octopus.socialnetwork.domain.usecase.notifications.FetchNotificationItemsUseCase
import com.octopus.socialnetwork.domain.usecase.notifications.FetchNotificationsUseCase
import com.octopus.socialnetwork.ui.screen.notifications.mapper.toNotificationsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(
    private val fetchNotifications: FetchNotificationsUseCase,
    private val fetchNotificationItems: FetchNotificationItemsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(NotificationsUiState())
    val state = _state.asStateFlow()

    init {
        getNotifications()
    }

    private fun getNotifications() {
        _state.update { it.copy(isLoading = true, isError = true) }
        viewModelScope.launch {
            try {
                val notifications = fetchNotifications().map { it.toNotificationsUiState() }
                _state.update {
                    it.copy(notifications = notifications, isLoading = false, isError = false,) }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, isError = true) }
            }
        }
    }


    @SuppressLint("SuspiciousIndentation")
    fun markViewedNotification(notification: NotificationItemsUiState) {
        viewModelScope.launch {
            try {
                if (!notification.viewed)
                fetchNotificationItems(notification.id)
                     getNotifications()
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, viewed = false, isError = true) }
            }
        }
    }


    fun onClickTryAgain() {
        getNotifications()
    }

}