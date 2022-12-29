package com.octopus.socialnetwork.domain.usecase.user

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.user.toFriends
import com.octopus.socialnetwork.domain.model.user.Friends
import javax.inject.Inject


class FetchFriendsUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
) {
    suspend operator fun invoke(currentUserId: Int): Friends {
        return socialRepository.getFriends(currentUserId).toFriends()
    }
}