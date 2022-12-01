package com.octopus.socialnetwork.data.repository.social

import com.octopus.socialnetwork.data.remote.response.dto.user.UserDetailsDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserFriendsDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserPostsDTO

interface SocialRepository {
    suspend fun getUserDetails(visitedUserId: Int): UserDetailsDTO
    suspend fun getUserFriends(visitedUserId: Int): UserFriendsDTO
    suspend fun getUserPosts(visitedUserId: Int, currentUserId: Int): UserPostsDTO
}