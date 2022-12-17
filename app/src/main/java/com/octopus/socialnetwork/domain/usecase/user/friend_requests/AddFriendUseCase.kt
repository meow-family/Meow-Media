package com.octopus.socialnetwork.domain.usecase.user.friend_requests

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.user.toCheckUserFriend
import com.octopus.socialnetwork.domain.model.user.FriendValidator
import com.octopus.socialnetwork.domain.usecase.user.FetchUserIdUseCase
import javax.inject.Inject

class AddFriendUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
    private val fetchUserIdUseCase: FetchUserIdUseCase
) {
    suspend operator fun invoke(userIdWantedToAdd: Int): FriendValidator {
        return socialRepository.addFriend(
            fetchUserIdUseCase.invoke(),
            userIdWantedToAdd
        ).toCheckUserFriend()
    }
}