package com.octopus.socialnetwork.domain.usecase.user.friend_requests

import android.util.Log
import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.user.friend_requests.toCheckUserFriend
import com.octopus.socialnetwork.domain.model.user.FriendValidator
import com.octopus.socialnetwork.domain.usecase.authentication.FetchUserIdUseCase
import javax.inject.Inject

class ToggleFriendshipUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
    private val fetchUserIdUseCase: FetchUserIdUseCase
) {
    suspend operator fun invoke(userIdWantedToAdd: Int, isFriend: Boolean): FriendValidator {
        Log.i("MEOW","userIdWantedToAdd usecase $userIdWantedToAdd")
        return if (isFriend) {
            Log.i("FRIEND","removing friend")
            socialRepository.removeFriend(
                myUserId = fetchUserIdUseCase(),
                userIdWantedToAdd = userIdWantedToAdd
            ).toCheckUserFriend()
        } else {
            Log.i("FRIEND","adding friend")
            socialRepository.addFriend(
                myUserId = fetchUserIdUseCase(),
                userIdWantedToAdd = userIdWantedToAdd
            ).toCheckUserFriend()
        }

    }
}