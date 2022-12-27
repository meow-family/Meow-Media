package com.octopus.socialnetwork.domain.usecase.notifications

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.notifications.toUserNotifications
import com.octopus.socialnetwork.domain.model.notifications.NotificationItems
import com.octopus.socialnetwork.domain.model.notifications.Notifications
import com.octopus.socialnetwork.domain.usecase.authentication.FetchUserIdUseCase
import com.octopus.socialnetwork.domain.utils.Constants.GROUP_JOIN_REQUEST
import com.octopus.socialnetwork.domain.utils.Constants.POKE
import okhttp3.internal.filterList
import javax.inject.Inject

class FetchNotificationsUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
    private val fetchUserIdUseCase: FetchUserIdUseCase,
) {
    suspend operator fun invoke(): List<NotificationItems> {
        val notification =
            socialRepository.getNotifications(fetchUserIdUseCase()).toUserNotifications()
        return filterNotification(notification)

    }

    private fun filterNotification(item: Notifications): List<NotificationItems> {
        return item.notifications.filterList {
            this.notification.type !in listOf(GROUP_JOIN_REQUEST, POKE)
        }
    }


}


