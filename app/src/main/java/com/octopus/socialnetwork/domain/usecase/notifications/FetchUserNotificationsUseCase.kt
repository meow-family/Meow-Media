package com.octopus.socialnetwork.domain.usecase.notifications

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.notifications.toUserNotifications
import com.octopus.socialnetwork.domain.model.notifications.NotificationItems
import com.octopus.socialnetwork.domain.model.notifications.UserNotifications
import com.octopus.socialnetwork.domain.usecase.authentication.FetchUserIdUseCase
import kotlinx.coroutines.flow.last
import okhttp3.internal.filterList
import javax.inject.Inject

class FetchUserNotificationsUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
    private val fetchUserIdUseCase: FetchUserIdUseCase,
) {
    suspend operator fun invoke(

    ): List<NotificationItems> {
        val notification =
            socialRepository.getUserNotifications(fetchUserIdUseCase())
                .toUserNotifications()
        return filterNotification(notification)

    }

    private fun filterNotification(item: UserNotifications): List<NotificationItems> {
        return item.notifications.filterList {
            this.notification.type !in listOf("group:joinrequest", "ossnpoke:poke")
        }
    }


}


