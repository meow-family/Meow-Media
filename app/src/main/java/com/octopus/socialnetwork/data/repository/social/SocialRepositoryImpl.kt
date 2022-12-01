package com.octopus.socialnetwork.data.repository.social

import com.octopus.socialnetwork.data.remote.response.dto.user.UserDetailsDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserFriendsDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserPostsDTO
import com.octopus.socialnetwork.data.remote.service.SocialService
import javax.inject.Inject

class SocialRepositoryImpl @Inject constructor(
    private val socialService: SocialService,
) : SocialRepository {

    override suspend fun getUserDetails(guid: Int): UserDetailsDTO {
        return socialService.userDetails(guid).payload
    }

    override suspend fun getUserFriends(guid: Int): UserFriendsDTO {
        return socialService.userFriends(guid).payload
    }

    override suspend fun getUserPosts(uguid: Int, guid: Int): UserPostsDTO {
        return socialService.userPosts(uguid, guid).payload
    }

}