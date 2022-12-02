package com.octopus.socialnetwork.data.repository.social

import android.util.Log
import com.octopus.socialnetwork.data.remote.response.dto.notifications.NotificationItemsDTO
import com.octopus.socialnetwork.data.remote.response.dto.notifications.UserNotificationsCountDTO
import com.octopus.socialnetwork.data.remote.response.dto.notifications.UserNotificationsDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserDetailsDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserFriendsDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserPostsDTO
import com.octopus.socialnetwork.data.remote.service.SocialService
import javax.inject.Inject

class SocialRepositoryImpl @Inject constructor(
    private val socialService: SocialService,
) : SocialRepository {

    override suspend fun getUserDetails(visitedUserId: Int): UserDetailsDTO {
        return socialService.getUserDetails(visitedUserId).payload
    }

    override suspend fun getUserFriends(visitedUserId: Int): UserFriendsDTO {
        return socialService.getUserFriends(visitedUserId).payload
    }

    override suspend fun getUserPosts(visitedUserId: Int, currentUserId: Int): UserPostsDTO {
        return socialService.getUserPosts(visitedUserId, currentUserId).payload
    }

//----------------------------------- Notifications -----------------------------------//
    override suspend fun getUserNotifications(currentUserId: Int, //types: String, offset: Int
    ): UserNotificationsDTO {
        return socialService.getUserNotifications(currentUserId, //types, offset
        ).payload
    }

    override suspend fun getUserNotificationsCount(currentUserId: Int, //types: String
    ): UserNotificationsCountDTO {
        return socialService.getUserNotificationsCount(currentUserId, //types
        ).payload
    }

    override suspend fun markUserNotificationsAsViewed(notificationId: Int): NotificationItemsDTO {
        return socialService.markUserNotificationsAsViewed(notificationId).payload
    }

}