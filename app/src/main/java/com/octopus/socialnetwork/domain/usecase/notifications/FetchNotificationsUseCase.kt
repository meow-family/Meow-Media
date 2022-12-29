package com.octopus.socialnetwork.domain.usecase.notifications

import androidx.paging.PagingData
import androidx.paging.map
import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.notifications.toNotificationItems
import com.octopus.socialnetwork.domain.model.notifications.NotificationItem
import com.octopus.socialnetwork.domain.model.notifications.Notifications
import com.octopus.socialnetwork.domain.usecase.authentication.FetchUserIdUseCase
import com.octopus.socialnetwork.domain.utils.Constants.GROUP_JOIN_REQUEST
import com.octopus.socialnetwork.domain.utils.Constants.POKE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okhttp3.internal.filterList
import javax.inject.Inject

class FetchNotificationsUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
    private val fetchUserIdUseCase: FetchUserIdUseCase,
) {
    suspend operator fun invoke(): Flow<PagingData<List<NotificationItem>>> {
        return socialRepository.getNotificationsPager(fetchUserIdUseCase()).flow.map { pager ->
            pager.map { notificationItemDto ->
                notificationItemDto.toNotificationItems()
                listOf<NotificationItem>()
            }
        }
    }

    private fun filterNotifications(list: List<NotificationItem>): List<NotificationItem> {
        return list.filterList {
            this.notification.type !in listOf(GROUP_JOIN_REQUEST, POKE)
        }
    }

}


