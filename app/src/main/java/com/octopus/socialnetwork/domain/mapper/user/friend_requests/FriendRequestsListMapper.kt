package com.octopus.socialnetwork.domain.mapper.user.friend_requests

import com.octopus.socialnetwork.data.remote.response.dto.user.friend_requests.FriendRequestsListDTO
import com.octopus.socialnetwork.domain.mapper.user.toUserDetails
import com.octopus.socialnetwork.domain.model.user.UserDetails

fun FriendRequestsListDTO.toFriendRequestsList(): List<UserDetails> {
    return this.requests.map {
        it.toUserDetails()
    }
}