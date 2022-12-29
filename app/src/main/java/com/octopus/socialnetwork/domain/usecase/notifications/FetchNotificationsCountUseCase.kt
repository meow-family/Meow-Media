package com.octopus.socialnetwork.domain.usecase.notifications

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.notifications.toNotificationsCount
import com.octopus.socialnetwork.domain.model.notifications.NotificationsCount
import com.octopus.socialnetwork.domain.usecase.authentication.FetchUserIdUseCase
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class FetchNotificationsCountUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
    private val fetchUserIdUseCase: FetchUserIdUseCase,
) {
    suspend operator fun invoke() : NotificationsCount {
        return socialRepository.getNotificationsCount(fetchUserIdUseCase().first()).toNotificationsCount()
    }
}