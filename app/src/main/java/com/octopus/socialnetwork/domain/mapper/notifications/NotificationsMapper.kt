package com.octopus.socialnetwork.domain.mapper.notifications

import com.octopus.socialnetwork.data.remote.response.dto.notifications.*
import com.octopus.socialnetwork.domain.model.notifications.*

fun UserNotificationsDTO.asUserNotifications(): UserNotifications {
    return UserNotifications(
        notifications = list?.map { it.asNotificationItems() } ?: emptyList(),
        count = count ?: 0,
        offset = offset ?: 0
    )
}

fun NotificationItemsDto.asNotificationItems(): NotificationItems {
    return NotificationItems(
        notification = notification?.asNotification() ?: Notification(0,"",0,0,0,"",0L,0 ),
        postOwner = postOwner?.asPoster() ?: PostOwner(0,"",""),
        entity = entity ?: false,
        post = post ?: false,
        group = group?.asGroup() ?: Group(0,"",false),
    )
}

fun NotificationDto.asNotification(): Notification {
    return Notification(
        guid = guid ?: 0,
        type = type ?: "",
        posterId = posterGuid ?: 0,
        ownerId = ownerGuid ?: 0,
        subjectId = subjectGuid ?: 0,
        viewed = viewed ?: "",
        timeCreated = timeCreated ?: 0L,
        itemId = itemGuid ?: 0,
    )
}

fun PostOwnerDto.asPoster(): PostOwner {
    return PostOwner(
        userId = userId ?: 0,
        fullName = fullName ?: "",
        icon = icon ?: "",
    )
}

fun GroupDto.asGroup(): Group {
    return Group(
        guid = guid ?: 0,
        title = title ?: "",
        isMember = isMember ?: false,
    )
}

fun UserNotificationsCountDto.asUserNotificationsCount(): UserNotificationsCount {
    return UserNotificationsCount(
        notifications = notifications ?: 0,
        messages = messages ?: 0,
        friends = friends ?: 0,
    )
}