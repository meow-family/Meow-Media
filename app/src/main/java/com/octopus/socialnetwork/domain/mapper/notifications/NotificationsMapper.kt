package com.octopus.socialnetwork.domain.mapper.notifications

import com.octopus.socialnetwork.data.remote.response.dto.notifications.*
import com.octopus.socialnetwork.domain.model.notifications.*

fun UserNotificationsDTO.asUserNotifications(): UserNotifications {
    return UserNotifications(
        list = list?.map { it.asNotificationItems() } ?: emptyList(),
        count = count ?: 0,
        offset = offset ?: 0
    )
}

fun NotificationItemsDTO.asNotificationItems(): NotificationItems {
    return NotificationItems(
        notification = notification?.asNotification() ?: Notification(0,"",0,0,0,"",0L,0 ),
        poster = poster?.asPoster() ?: Poster(0,"",""),
        entity = entity ?: false,
        post = post ?: false,
        group = group?.asGroup() ?: Group(0,"",false),
    )
}

fun NotificationDTO.asNotification(): Notification {
    return Notification(
        guid = guid ?: 0,
        type = type ?: "",
        posterGuid = posterGuid ?: 0,
        ownerGuid = ownerGuid ?: 0,
        subjectGuid = subjectGuid ?: 0,
        viewed = viewed ?: "",
        timeCreated = timeCreated ?: 0L,
        itemGuid = itemGuid ?: 0,
    )
}

fun PosterDTO.asPoster(): Poster {
    return Poster(
        guid = guid ?: 0,
        fullName = fullName ?: "",
        icon = icon ?: "",
    )
}

fun GroupDTO.asGroup(): Group {
    return Group(
        guid = guid ?: 0,
        title = title ?: "",
        isMember = isMember ?: false,
    )
}

fun UserNotificationsCountDTO.asUserNotificationsCount(): UserNotificationsCount {
    return UserNotificationsCount(
        notifications = notifications ?: 0,
        messages = messages ?: 0,
        friends = friends ?: 0,
    )
}