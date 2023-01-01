package com.octopus.socialnetwork.domain.usecase.notifications

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.notifications.toNotificationItems
import com.octopus.socialnetwork.domain.model.notifications.NotificationItem
import javax.inject.Inject

class FetchNotificationItemsUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
) {
    suspend operator fun invoke(notificationId: Int) : NotificationItem {
        return socialRepository.markUserNotificationsAsViewed(notificationId).toNotificationItems()
    }
}