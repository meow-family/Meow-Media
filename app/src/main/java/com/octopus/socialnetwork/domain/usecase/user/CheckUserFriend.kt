package com.octopus.socialnetwork.domain.usecase.user

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.user.asCheckUserFriend
import com.octopus.socialnetwork.domain.model.user.UserFriendChecker
import javax.inject.Inject


class CheckUserFriendUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
) {
    suspend operator fun invoke(userId: Int, userIdWantedToCheck: Int): UserFriendChecker {
        return socialRepository.checkUserFriend(
            userId,
            userIdWantedToCheck
        ).asCheckUserFriend()
    }

}