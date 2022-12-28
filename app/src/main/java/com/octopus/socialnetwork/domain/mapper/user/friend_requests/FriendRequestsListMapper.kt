package com.octopus.socialnetwork.domain.mapper.user.friend_requests

import com.octopus.socialnetwork.data.remote.response.dto.user.friend_requests.FriendRequestsResponse
import com.octopus.socialnetwork.domain.mapper.user.toUser
import com.octopus.socialnetwork.domain.model.user.User

fun FriendRequestsResponse.toFriendRequestsList(): List<User> {
    return this.requests?.map {
        it.toUser()
    }?: emptyList()
}