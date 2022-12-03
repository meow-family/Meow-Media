package com.octopus.socialnetwork.domain.mapper.user

import com.octopus.socialnetwork.data.remote.response.dto.user.CheckUserFriendDTO
import com.octopus.socialnetwork.domain.model.user.UserFriendChecker


fun CheckUserFriendDTO.asCheckUserFriend(): UserFriendChecker {
    return UserFriendChecker(
        isFriend = isFriend ?: false,
        requestExists = requestExists ?: false
    )
}
