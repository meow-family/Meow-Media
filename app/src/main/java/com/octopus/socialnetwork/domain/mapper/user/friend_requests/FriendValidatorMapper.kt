package com.octopus.socialnetwork.domain.mapper.user.friend_requests

import com.octopus.socialnetwork.data.remote.response.dto.user.friend_requests.FriendValidatorResponse
import com.octopus.socialnetwork.domain.model.user.FriendValidator

fun FriendValidatorResponse.toCheckUserFriend(): FriendValidator {
    return FriendValidator(
        isFriend = isFriend ?: false,
        requestExists = requestExists ?: false,
        success = success ?: false,
    )
}
