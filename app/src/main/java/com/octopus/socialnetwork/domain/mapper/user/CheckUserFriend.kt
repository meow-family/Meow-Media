package com.octopus.socialnetwork.domain.mapper.user

import com.octopus.socialnetwork.data.remote.response.dto.user.CheckUserFriendDTO
import com.octopus.socialnetwork.domain.model.user.CheckUserFriend


fun CheckUserFriendDTO.asCheckUserFriend(): CheckUserFriend {
    return CheckUserFriend(
        isFriend = isFriend ?: false,
        requestExists = requestExists ?: false
    )
}
