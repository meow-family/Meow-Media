package com.octopus.socialnetwork.domain.usecase.user_details

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.user_details.asUserFriends
import com.octopus.socialnetwork.domain.model.user_details.UserFriends
import javax.inject.Inject

class FetchUserFriendsUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
) {
    suspend operator fun invoke(guid: Int) : UserFriends {
        return socialRepository.getUserFriends(guid).asUserFriends()
    }
}