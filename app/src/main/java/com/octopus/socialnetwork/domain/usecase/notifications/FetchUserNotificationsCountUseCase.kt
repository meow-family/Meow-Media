package com.octopus.socialnetwork.domain.usecase.notifications

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.notifications.toUserNotificationsCount
import com.octopus.socialnetwork.domain.model.notifications.UserNotificationsCount
import javax.inject.Inject

class FetchUserNotificationsCountUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
) {
    suspend operator fun invoke(currentUserId: Int, types: String?) : UserNotificationsCount {
        return socialRepository.getUserNotificationsCount(currentUserId, types ?: "").toUserNotificationsCount()
    }
}