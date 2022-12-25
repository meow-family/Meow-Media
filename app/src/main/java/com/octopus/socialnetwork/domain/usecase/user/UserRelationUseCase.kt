package com.octopus.socialnetwork.domain.usecase.user

import com.octopus.socialnetwork.domain.enums.UserRelation
import com.octopus.socialnetwork.domain.usecase.authentication.FetchUserIdUseCase
import com.octopus.socialnetwork.domain.usecase.user.friend_requests.CheckUserIsFriendUseCase
import kotlinx.coroutines.flow.last
import javax.inject.Inject


class UserRelationUseCase @Inject constructor(
    private val fetchUserIdUseCase: FetchUserIdUseCase,
    private val CheckUserIsFriendUseCase: CheckUserIsFriendUseCase
) {
    suspend operator fun invoke(userIdWantedToCheck: Int): UserRelation {
        val userState = CheckUserIsFriendUseCase(userIdWantedToCheck)
        if (fetchUserIdUseCase().last() == userIdWantedToCheck) {
            return UserRelation.ME
        } else if (userState.isFriend) {
            return UserRelation.IS_FRIEND
        } else if (userState.requestExists) {
            return UserRelation.REQUESTED
        }

        return UserRelation.NOT_FRIEND

    }
}