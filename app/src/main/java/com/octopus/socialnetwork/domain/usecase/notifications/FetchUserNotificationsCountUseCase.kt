package com.octopus.socialnetwork.domain.usecase.notifications

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.notifications.toUserNotificationsCount
import com.octopus.socialnetwork.domain.model.notifications.UserNotificationsCount
import com.octopus.socialnetwork.domain.usecase.authentication.FetchUserIdUseCase
import kotlinx.coroutines.flow.last
import javax.inject.Inject

class FetchUserNotificationsCountUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
    private val fetchUserIdUseCase: FetchUserIdUseCase,
) {
    suspend operator fun invoke() : UserNotificationsCount {
        return socialRepository.getUserNotificationsCount(fetchUserIdUseCase()).toUserNotificationsCount()
    }
}