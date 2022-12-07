package com.octopus.socialnetwork.ui.screen.notifications.mapper

import com.octopus.socialnetwork.domain.model.notifications.Notification
import com.octopus.socialnetwork.domain.model.notifications.NotificationItems
import com.octopus.socialnetwork.domain.model.notifications.PostOwner
import com.octopus.socialnetwork.domain.model.notifications.UserNotifications
import com.octopus.socialnetwork.ui.screen.notifications.state.NotificationDetailsUiState
import com.octopus.socialnetwork.ui.screen.notifications.state.NotificationItemsUiState
import com.octopus.socialnetwork.ui.screen.notifications.state.NotificationPosterUiState
import com.octopus.socialnetwork.ui.screen.notifications.state.NotificationsUiState

fun UserNotifications.toNotificationsUiState(): NotificationsUiState {
    return NotificationsUiState(
        notifications = notifications.map { NotificationItems ->
            NotificationItems.toNotificationItemsUiState()
        }
    )
}


fun NotificationItems.toNotificationItemsUiState(): NotificationItemsUiState {
    return NotificationItemsUiState(
        notificationDetails = notification.toNotificationDetailsUiState(),
        posterDetails = postOwner.toNotificationPosterUiState()
    )
}

fun Notification.toNotificationDetailsUiState(): NotificationDetailsUiState {
    return NotificationDetailsUiState(
        id = guid,
        type = type,
        posterId = posterId,
        ownerId = ownerId,
        subjectId = subjectId,
        viewed = viewed,
        timeCreated = timeCreated,
        itemId = itemId
    )
}

fun PostOwner.toNotificationPosterUiState(): NotificationPosterUiState {
    return NotificationPosterUiState(
        posterId = userId,
        posterFullName = fullName,
        posterAvatar = icon
    )
}