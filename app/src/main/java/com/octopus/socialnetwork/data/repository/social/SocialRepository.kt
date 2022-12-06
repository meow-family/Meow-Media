package com.octopus.socialnetwork.data.repository.social

import com.octopus.socialnetwork.data.remote.response.base.BaseResponse
import com.octopus.socialnetwork.data.remote.response.dto.album.AlbumsDto
import com.octopus.socialnetwork.data.remote.response.dto.album.album_photos_list.AlbumPhotosDTO
import com.octopus.socialnetwork.data.remote.response.dto.comment.CommentDTO
import com.octopus.socialnetwork.data.remote.response.dto.comment.edit.CommentEditionDTO
import com.octopus.socialnetwork.data.remote.response.dto.like.LikeDTO
import com.octopus.socialnetwork.data.remote.response.dto.messages.list_messages.MessageListDTO
import com.octopus.socialnetwork.data.remote.response.dto.messages.message_send.SendMessageDTO
import com.octopus.socialnetwork.data.remote.response.dto.messages.recent_messages.RecentMessagesDTO
import com.octopus.socialnetwork.data.remote.response.dto.messages.unread_message.UnreadMessagesDTO
import com.octopus.socialnetwork.data.remote.response.dto.notifications.NotificationItemsDTO
import com.octopus.socialnetwork.data.remote.response.dto.notifications.UserNotificationsCountDTO
import com.octopus.socialnetwork.data.remote.response.dto.notifications.UserNotificationsDTO
import com.octopus.socialnetwork.data.remote.response.dto.photo.delete_photo.ProfilePhotoDeletion
import com.octopus.socialnetwork.data.remote.response.dto.photo.photoDetails.Photo
import com.octopus.socialnetwork.data.remote.response.dto.photo.photoDetails.PhotoDTO
import com.octopus.socialnetwork.data.remote.response.dto.photo.photo_profile.UserProfileDTO
import com.octopus.socialnetwork.data.remote.response.dto.post.AllPostDTO
import com.octopus.socialnetwork.data.remote.response.dto.post.PostDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.CheckUserFriendDTO
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
    ): CheckUserFriendDTO

    suspend fun getUserPosts(visitedUserId: Int, currentUserId: Int): UserPostsDTO
    //endregion

    //region message
    suspend fun getMessagesListRecent(userId: Int): RecentMessagesDTO

    suspend fun sendMessage(
        messageSenderId: Int,
        messageReceiverId: Int,
        message: String
    ): SendMessageDTO

    suspend fun unreadMessages(
        messageSenderId: Int,
        messageReceiverId: Int,
        markAllRead: String
    ): UnreadMessagesDTO

    suspend fun getMessagesList(messageSenderId: Int, messageReceiverId: Int): MessageListDTO

    //endregion

    //region post
    suspend fun viewPost(postId: Int, postOwnerId: Int): PostDTO

    suspend fun viewUserPosts(visitedUserId: Int, currentUserId: Int): BaseResponse<AllPostDTO>

    suspend fun viewNewsFeed(currentUserId: Int): List<PostDTO>

    suspend fun createPost(
        currentUserId: Int,
        posterOwnerId: Int,
        post: String,
        type: String
    ): PostDTO

    suspend fun deletePost(postId: Int, postOwnerId: Int): PostDTO
    suspend fun like(currentUserId: Int, contentId: Int, typeContent: String): LikeDTO

    suspend fun unlike(
        currentUserId: Int,
        contentId: Int,
        typeContent: String
    ): LikeDTO
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

    //region comment
    suspend fun getComments(currentUserId: Int, postId: Int, type: String): CommentDTO

    suspend fun editComment(
        commentId: Int,
        comment: String,
    ): CommentEditionDTO

    suspend fun deleteComment(
        commentId: Int,
        userId: Int,
    ): Boolean
    //endregion

    //region photo
    suspend fun getPhoto(
        photoId: Int,
        userId: Int,
    ): PhotoDTO

    suspend fun getPhotosListProfileCover(
        userId: Int,
        type: String,
    ): BaseResponse<List<Photo>>

    suspend fun getPhotoViewProfile(
        photoId: Int,
        userId: Int,
    ): BaseResponse<UserProfileDTO>

    suspend fun deletePhotoProfile(
        photoId: Int,
        userId: Int,
    ): BaseResponse<ProfilePhotoDeletion>

    suspend fun deleteProfileCover(
        photoId: Int,
        userId: Int,
    ): BaseResponse<ProfilePhotoDeletion>

//endregion

}