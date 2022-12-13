package com.octopus.socialnetwork.domain.usecase.user

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.user.toCheckUserFriend
import com.octopus.socialnetwork.domain.model.user.FriendValidator
import javax.inject.Inject


class CheckUserFriendUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
) {
    suspend operator fun invoke(userId: Int, userIdWantedToCheck: Int): FriendValidator {
        return socialRepository.checkUserFriend(
            userId,
            userIdWantedToCheck
        ).toCheckUserFriend()
    }

}