package com.octopus.socialnetwork.data.repository.social

import com.octopus.socialnetwork.data.remote.response.dto.user.UserDetailsDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserFriendsDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserPostsDTO

interface SocialRepository {
    suspend fun getUserDetails(guid: Int): UserDetailsDTO
    suspend fun getUserFriends(guid: Int): UserFriendsDTO
    suspend fun getUserPosts(uguid: Int, guid: Int): UserPostsDTO
}