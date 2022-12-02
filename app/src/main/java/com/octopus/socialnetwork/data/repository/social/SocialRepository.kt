package com.octopus.socialnetwork.data.repository.social

import com.octopus.socialnetwork.data.remote.response.dto.notifications.NotificationItemsDTO
import com.octopus.socialnetwork.data.remote.response.dto.notifications.UserNotificationsCountDTO
import com.octopus.socialnetwork.data.remote.response.dto.notifications.UserNotificationsDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserDetailsDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserFriendsDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserPostsDTO

interface SocialRepository {
    suspend fun getUserDetails(visitedUserId: Int): UserDetailsDTO
    suspend fun getUserFriends(visitedUserId: Int): UserFriendsDTO
    suspend fun getUserPosts(visitedUserId: Int, currentUserId: Int): UserPostsDTO

    suspend fun getUserNotifications(currentUserId: Int,//types: String, offset:Int
    ): UserNotificationsDTO
    suspend fun getUserNotificationsCount(currentUserId: Int,//types: String
    ): UserNotificationsCountDTO
    suspend fun markUserNotificationsAsViewed(notificationId: Int): NotificationItemsDTO
}