package com.octopus.socialnetwork.domain.usecase.notifications

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.notifications.asUserNotifications
import com.octopus.socialnetwork.domain.model.notifications.UserNotifications
import javax.inject.Inject

class FetchUserNotificationsUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
) {
    suspend operator fun invoke(currentUserId: Int) : UserNotifications {
        return socialRepository.getUserNotifications(currentUserId).asUserNotifications()
    }
}