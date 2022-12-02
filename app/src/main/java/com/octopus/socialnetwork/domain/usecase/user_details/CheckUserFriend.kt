package com.octopus.socialnetwork.domain.usecase.user_details

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.user.asCheckUserFriend
import com.octopus.socialnetwork.domain.model.user.CheckUserFriend
import javax.inject.Inject


class CheckUserFriendUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
) {
    suspend operator fun invoke(userId: Int, userIdWantedToCheck: Int): CheckUserFriend {
        return socialRepository.checkUserFriend(
            userId,
            userIdWantedToCheck
        ).payload.asCheckUserFriend()
    }

}