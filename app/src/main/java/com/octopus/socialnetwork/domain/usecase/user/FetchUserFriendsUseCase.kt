package com.octopus.socialnetwork.domain.usecase.user

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.user.asUserFriends
import com.octopus.socialnetwork.domain.model.user.UserFriends
import javax.inject.Inject

class FetchUserFriendsUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
) {
    suspend operator fun invoke(guid: Int) : UserFriends {
        return socialRepository.getUserFriends(guid).asUserFriends()
    }
}