package com.octopus.socialnetwork.data.repository.social

import com.octopus.socialnetwork.data.remote.response.dto.album.AlbumsDto
import com.octopus.socialnetwork.data.remote.response.dto.album.album_photos_list.AlbumPhotosDTO
import com.octopus.socialnetwork.data.remote.response.dto.base.BaseResponse
import com.octopus.socialnetwork.data.remote.response.dto.like.LikeDTO
import com.octopus.socialnetwork.data.remote.response.dto.post.PostDetailsDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.CheckUserFriendDTO
import com.octopus.socialnetwork.data.remote.response.dto.notifications.NotificationItemsDTO
import com.octopus.socialnetwork.data.remote.response.dto.notifications.UserNotificationsCountDTO
import com.octopus.socialnetwork.data.remote.response.dto.notifications.UserNotificationsDTO
import com.octopus.socialnetwork.data.remote.response.dto.post.PostDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserDetailsDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserFriendsDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserPostsDTO

interface SocialRepository {

    //region user
    suspend fun getUserDetails(visitedUserId: Int): UserDetailsDTO
    suspend fun getUserFriends(visitedUserId: Int): UserFriendsDTO
    suspend fun checkUserFriend(
        currentUserId: Int,
        userIdWantedToCheck: Int
    ): BaseResponse<CheckUserFriendDTO>

    suspend fun getUserPosts(visitedUserId: Int, currentUserId: Int): UserPostsDTO
    //endregion

    //region post
    suspend fun viewPost(postId: Int, userId: Int): BaseResponse<PostDetailsDTO>

    suspend fun viewUserPosts(
        visitedUserId: Int,
        currentUserId: Int
    ): List<BaseResponse<PostDetailsDTO>>

    suspend fun viewNewsFeed(userId: Int): List<BaseResponse<PostDetailsDTO>>

    suspend fun createPost(
        currentUserId: Int,
        posterOwnerId: Int,
        post: String,
        type: String
    ): BaseResponse<PostDTO>

    suspend fun deletePost(postId: Int, userId: Int): BaseResponse<PostDetailsDTO>
    suspend fun like(currentUserId: Int, contentId: Int, typeContent: String): BaseResponse<LikeDTO>

    suspend fun unlike(
        currentUserId: Int,
        contentId: Int,
        typeContent: String
    ): BaseResponse<LikeDTO>
    //endregion

    //region album
    suspend fun getAlbumsUser(albumOwnerUserId: Int, viewerUserId: Int): AlbumsDto
    suspend fun getAlbumPhotos(albumId: Int): AlbumPhotosDTO
    suspend fun createAlbum(title: String, currentUserId: Int, privacy: Int): Int
    suspend fun deleteAlbumPhoto(photoId: Int, visitedUserId: Int): Boolean
    //endregion

    //region notifications
    suspend fun getUserNotifications(
        currentUserId: Int,
        types: String?,
        offset: Int?
    ): UserNotificationsDTO

    suspend fun getUserNotificationsCount(
        currentUserId: Int,
        types: String?
    ): UserNotificationsCountDTO

    suspend fun markUserNotificationsAsViewed(notificationId: Int): NotificationItemsDTO
    //endregion

}