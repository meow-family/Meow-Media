package com.octopus.socialnetwork.domain.usecase.user.friend_requests

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.user.friend_requests.toFriendRequestsList
import com.octopus.socialnetwork.domain.model.user.UserDetails
import com.octopus.socialnetwork.domain.usecase.authentication.FetchUserIdUseCase
import kotlinx.coroutines.flow.last
import javax.inject.Inject


class FetchFriendRequestsListUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
    private val fetchUserIdUseCase: FetchUserIdUseCase,

    ) {
    suspend operator fun invoke(): List<UserDetails> {
        return socialRepository.getFriendRequests(fetchUserIdUseCase.invoke().last()).toFriendRequestsList()
    }
}

