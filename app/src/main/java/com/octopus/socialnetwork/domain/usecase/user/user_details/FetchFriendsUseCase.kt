package com.octopus.socialnetwork.domain.usecase.user.user_details

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.user.toFriends
import com.octopus.socialnetwork.domain.model.user.Friends
import javax.inject.Inject

class FetchFriendsUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
) {
    suspend operator fun invoke(myUserId: Int) : Friends {
        return socialRepository.getFriends(myUserId).toFriends()
    }
}