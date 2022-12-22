package com.octopus.socialnetwork.domain.mapper.notifications

import com.octopus.socialnetwork.constants.Constants.INVALID_USER_ID
import com.octopus.socialnetwork.data.remote.response.dto.notifications.*
import com.octopus.socialnetwork.domain.model.notifications.*
import com.octopus.socialnetwork.ui.util.extensions.toFormattedDate
import java.util.*

fun UserNotificationsDTO.toUserNotifications(): UserNotifications {
    return UserNotifications(
        notifications = list?.map { it.toNotificationItems() } ?: emptyList(),
        count = count ?: 0,
        offset = offset ?: 0
    )
}

fun NotificationItemsDto.toNotificationItems(): NotificationItems {
    return NotificationItems(
        notification = notification?.toNotification() ?: Notification(
            0,
            "",
            0,
            0,
            0,
            false,
            Date(),
            0
        ),
        postOwner = postOwner?.toPoster() ?: PostOwner(0, "", ""),
        entity = entity ?: false,
        post = post ?: false,
        group = group?.toGroup() ?: Group(0, "", false),
    )
}

fun NotificationDto.toNotification(): Notification {
    return Notification(
        guid = guid ?: INVALID_USER_ID,
        type = type ?: "",
        posterId = posterGuid ?: INVALID_USER_ID,
        ownerId = ownerGuid ?: INVALID_USER_ID,
        subjectId = subjectGuid ?: 0,
        viewed = viewed != null,
        timeCreated = timeCreated.toFormattedDate(),
        itemId = itemGuid ?: 0,
    )
}

fun PostOwnerDto.toPoster(): PostOwner {
    return PostOwner(
        userId = userId ?: INVALID_USER_ID,
        fullName = fullName ?: "",
        icon = icon ?: "",
    )
}

fun GroupDto.toGroup(): Group {
    return Group(
        guid = guid ?: 0,
        title = title ?: "",
        isMember = isMember ?: false,
    )
}

fun UserNotificationsCountDto.toUserNotificationsCount(): UserNotificationsCount {
    return UserNotificationsCount(
        notifications = notifications ?: 0,
        messages = messages ?: 0,
        friends = friends ?: 0,
    )
}