package com.octopus.socialnetwork.data.repository.social

import com.octopus.socialnetwork.data.remote.response.dto.album.AlbumsDto
import com.octopus.socialnetwork.data.remote.response.dto.album.album_photos_list.AlbumPhotosDTO
import com.octopus.socialnetwork.data.remote.response.dto.base.BaseResponse
import com.octopus.socialnetwork.data.remote.response.dto.comment.CommentDTO
import com.octopus.socialnetwork.data.remote.response.dto.comment.edit.CommentEditionDTO
import com.octopus.socialnetwork.data.remote.response.dto.like.LikeDTO
import com.octopus.socialnetwork.data.remote.response.dto.post.PostDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.CheckUserFriendDTO
import com.octopus.socialnetwork.data.remote.response.dto.notifications.NotificationItemsDTO
import com.octopus.socialnetwork.data.remote.response.dto.notifications.UserNotificationsCountDTO
import com.octopus.socialnetwork.data.remote.response.dto.notifications.UserNotificationsDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserFriendsDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserPostsDTO
import retrofit2.http.Query

interface SocialRepository {

    //region user
    suspend fun getUserDetails(visitedUserId: Int): UserDTO
    suspend fun getUserFriends(visitedUserId: Int): UserFriendsDTO
    suspend fun checkUserFriend(
        currentUserId: Int,
        userIdWantedToCheck: Int
    ): BaseResponse<CheckUserFriendDTO>

    suspend fun getUserPosts(visitedUserId: Int, currentUserId: Int): UserPostsDTO
    //endregion

    //region post
    suspend fun viewPost(postId: Int, userId: Int): BaseResponse<PostDTO>

    suspend fun viewUserPosts(visitedUserId: Int, currentUserId: Int): List<BaseResponse<PostDTO>>

    suspend fun viewNewsFeed(userId: Int): List<BaseResponse<PostDTO>>

//    suspend fun createPost(): BaseResponse<PostDTO>

    suspend fun deletePost(postId: Int, userId: Int): BaseResponse<PostDTO>
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
    suspend fun getUserNotifications(currentUserId: Int, types: String?, offset:Int?): UserNotificationsDTO
    suspend fun getUserNotificationsCount(currentUserId: Int, types: String?): UserNotificationsCountDTO
    suspend fun markUserNotificationsAsViewed(notificationId: Int): NotificationItemsDTO
    //endregion

    //region comment
    suspend fun getComments(currentUserId: Int, postId: Int, type: String) : BaseResponse<CommentDTO>

    suspend fun editComment(
    commentId: Int,
    comment: String,
    ): BaseResponse<CommentEditionDTO>

    suspend fun deleteComment(
        commentId: Int,
        userId: Int,
    ) : BaseResponse<Boolean>
    //endregion
}