package com.octopus.socialnetwork.domain.usecase.notifications

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.notifications.toUserNotifications
import com.octopus.socialnetwork.domain.model.notifications.NotificationItems
import com.octopus.socialnetwork.domain.model.notifications.UserNotifications
import okhttp3.internal.filterList
import javax.inject.Inject

class FetchUserNotificationsUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
) {
    suspend operator fun invoke(
        currentUserId: Int,
        types: String?,
        offset: Int?
    ): List<NotificationItems> {
        val notification =
            socialRepository.getUserNotifications(currentUserId, types ?: "", offset ?: 1)
                .toUserNotifications()
        return filterNotification(notification)

    }

    private fun filterNotification(item: UserNotifications): List<NotificationItems> {
        return item.notifications.filterList {
            this.notification.type !in listOf("group:joinrequest", "ossnpoke:poke")
        }
    }


}


