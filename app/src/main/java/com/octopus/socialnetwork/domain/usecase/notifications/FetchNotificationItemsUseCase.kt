package com.octopus.socialnetwork.domain.usecase.notifications

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.notifications.asNotificationItems
import com.octopus.socialnetwork.domain.model.notifications.NotificationItems
import javax.inject.Inject

class FetchNotificationItemsUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
) {
    suspend operator fun invoke(currentUserId: Int) : NotificationItems {
        return socialRepository.markUserNotificationsAsViewed(currentUserId).asNotificationItems()
    }
}