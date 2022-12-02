package com.octopus.socialnetwork.data.repository.social

import com.octopus.socialnetwork.data.remote.response.dto.base.BaseResponse
import com.octopus.socialnetwork.data.remote.response.dto.like.LikeDTO
import com.octopus.socialnetwork.data.remote.response.dto.post.PostDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.CheckUserFriendDTO
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


    suspend fun viewPost(postId: Int, userId: Int): BaseResponse<PostDTO>

    suspend fun viewUserPosts(ownerId: Int, viewerId: Int): List<BaseResponse<PostDTO>>

    suspend fun viewNewsFeed(userId: Int): List<BaseResponse<PostDTO>>

    suspend fun createPost(): BaseResponse<PostDTO>

    suspend fun deletePost(postId: Int, userId: Int): BaseResponse<PostDTO>
    suspend fun like(currentUserId: Int, contentId: Int, typeContent: String): BaseResponse<LikeDTO>

    suspend fun unlike(currentUserId: Int, contentId: Int, typeContent: String): BaseResponse<LikeDTO>

    suspend fun checkUserFriend(currentUserId: Int, userIdWantedToCheck: Int): BaseResponse<CheckUserFriendDTO>


    suspend fun getUserNotifications(currentUserId: Int,//types: String, offset:Int
    ): UserNotificationsDTO
    suspend fun getUserNotificationsCount(currentUserId: Int,//types: String
    ): UserNotificationsCountDTO
    suspend fun markUserNotificationsAsViewed(notificationId: Int): NotificationItemsDTO
}