package com.octopus.socialnetwork.domain.usecase.user.friend_requests

import android.util.Log
import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.user.friend_requests.toCheckUserFriend
import com.octopus.socialnetwork.domain.model.user.FriendValidator
import com.octopus.socialnetwork.domain.usecase.authentication.FetchUserIdUseCase
import kotlinx.coroutines.flow.last
import javax.inject.Inject

class CheckUserFriendUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
    private val fetchUserIdUseCase: FetchUserIdUseCase,
) {
    suspend operator fun invoke( userIdWantedToCheck: Int): FriendValidator {
        Log.i("TESTING","check user friend $userIdWantedToCheck")

        return socialRepository.checkUserFriend(
            fetchUserIdUseCase().last(),
            userIdWantedToCheck
        )?.toCheckUserFriend() ?: throw Throwable("friend request response is null")
    }
}