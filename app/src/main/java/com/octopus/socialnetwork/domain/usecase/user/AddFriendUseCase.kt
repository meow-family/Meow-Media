package com.octopus.socialnetwork.domain.usecase.user

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.user.toCheckUserFriend
import com.octopus.socialnetwork.domain.model.user.FriendValidator
import javax.inject.Inject

class AddFriendUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
) {
    suspend operator fun invoke(userId: Int, userIdWantedToAdd: Int): FriendValidator {
        return socialRepository.addFriend(
            userId,
            userIdWantedToAdd
        ).toCheckUserFriend()
    }
}