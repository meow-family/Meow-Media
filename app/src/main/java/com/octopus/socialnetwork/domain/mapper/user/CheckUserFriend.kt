package com.octopus.socialnetwork.domain.mapper.user

import com.octopus.socialnetwork.data.remote.response.dto.user.CheckUserFriendDto
import com.octopus.socialnetwork.domain.model.user.UserFriendChecker


fun CheckUserFriendDto.toCheckUserFriend(): UserFriendChecker {
    return UserFriendChecker(
        isFriend = isFriend ?: false,
        requestExists = requestExists ?: false
    )
}
