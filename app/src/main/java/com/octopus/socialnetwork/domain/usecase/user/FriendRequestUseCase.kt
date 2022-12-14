package com.octopus.socialnetwork.domain.usecase.user

import com.octopus.socialnetwork.data.remote.response.dto.user.UserDto
import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.user.toCheckUserFriend
import com.octopus.socialnetwork.domain.model.user.UserFriendChecker
import javax.inject.Inject


class FriendRequestUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
) {
    suspend operator fun invoke(currentUserId: Int, otherUserId: Int): UserDto {
        return socialRepository.friendRequest(
            currentUserId, otherUserId
        )
    }
}