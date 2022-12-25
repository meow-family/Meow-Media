package com.octopus.socialnetwork.ui.screen.notifications.mapper

import com.octopus.socialnetwork.domain.model.notifications.NotificationItems
import com.octopus.socialnetwork.ui.screen.notifications.state.NotificationItemsUiState


fun NotificationItems.toNotificationsUiState(): NotificationItemsUiState {
    return NotificationItemsUiState(
        id = notification.notificationId,
        type = notification.type,
        posterId = notification.notificationUserId,
        ownerId = notification.subjectOwnerId,
        subjectId = notification.subjectId,
        viewed = notification.viewed,
        timeCreated = notification.timeCreated,
        itemId = notification.itemId,
        posterFullName = postOwner.fullName,
        posterAvatar = postOwner.icon
    )
}

