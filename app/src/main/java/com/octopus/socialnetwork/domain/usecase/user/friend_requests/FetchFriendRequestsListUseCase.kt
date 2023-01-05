package com.octopus.socialnetwork.domain.usecase.user.friend_requests

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.user.toUser
import com.octopus.socialnetwork.domain.model.user.User
import com.octopus.socialnetwork.domain.usecase.authentication.FetchUserIdUseCase
import javax.inject.Inject


class FetchFriendRequestsListUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
    private val fetchUserIdUseCase: FetchUserIdUseCase,

    ) {
    suspend operator fun invoke(): List<User> {
        return socialRepository.getFriendRequests(fetchUserIdUseCase()).requests?.map { it.toUser() }
            ?: emptyList()
    }
}

