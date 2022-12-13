package com.octopus.socialnetwork.data.repository.social

import com.octopus.socialnetwork.data.remote.response.base.BaseResponse
import com.octopus.socialnetwork.data.remote.response.dto.album.AlbumPhotosDto
import com.octopus.socialnetwork.data.remote.response.dto.album.AlbumsDto
import com.octopus.socialnetwork.data.remote.response.dto.comment.CommentDetails
import com.octopus.socialnetwork.data.remote.response.dto.comment.CommentEditionDto
import com.octopus.socialnetwork.data.remote.response.dto.like.LikeDto
import com.octopus.socialnetwork.data.remote.response.dto.notifications.NotificationItemsDto
import com.octopus.socialnetwork.data.remote.response.dto.notifications.UserNotificationsCountDto
import com.octopus.socialnetwork.data.remote.response.dto.notifications.UserNotificationsDTO
import com.octopus.socialnetwork.data.remote.response.dto.photo.Photo
import com.octopus.socialnetwork.data.remote.response.dto.photo.PhotoDto
import com.octopus.socialnetwork.data.remote.response.dto.photo.ProfilePhotoDeletion
import com.octopus.socialnetwork.data.remote.response.dto.photo.UserProfileDto
import com.octopus.socialnetwork.data.remote.response.dto.post.AllPostDto
import com.octopus.socialnetwork.data.remote.response.dto.post.PostDto
import com.octopus.socialnetwork.data.remote.response.dto.user.FriendValidatorDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserDto
import com.octopus.socialnetwork.data.remote.response.dto.user.UserFriendsDto
import com.octopus.socialnetwork.data.remote.response.dto.user.UserPostsDto

interface SocialRepository {

    //region user
    suspend fun getUserDetails(visitedUserId: Int): UserDto

    suspend fun getUserFriends(visitedUserId: Int): UserFriendsDto

    suspend fun checkUserFriend(currentUserId: Int, userIdWantedToCheck: Int): FriendValidatorDTO

    suspend fun getUserPosts(visitedUserId: Int, currentUserId: Int): UserPostsDto

    suspend fun editUser(currentUserId: Int, firstName: String, lastName: String,
        email: String, currentPassword: String, newPassword: String): UserDto

    suspend fun addFriend(currentUserId: Int, userIdWantedToAdd: Int): CheckUserFriendDto
    suspend fun removeFriend(currentUserId: Int, userIdWantedToAdd: Int): CheckUserFriendDto

    //endregion

    //region post
    suspend fun viewPost(postId: Int, currentUserId: Int): PostDto

    suspend fun viewUserPosts(visitedUserId: Int, currentUserId: Int): BaseResponse<AllPostDto>

    suspend fun viewNewsFeed(currentUserId: Int): List<PostDto>

    suspend fun createPost(currentUserId: Int, posterOwnerId: Int, post: String, type: String
    ): PostDto

    suspend fun deletePost(postId: Int, postOwnerId: Int): PostDto
    suspend fun like(currentUserId: Int, contentId: Int, typeContent: String): LikeDto

    suspend fun unlike(currentUserId: Int, contentId: Int, typeContent: String): LikeDto
    //endregion

    //region album
    suspend fun getAlbumsUser(albumOwnerUserId: Int, viewerUserId: Int): AlbumsDto

    suspend fun getAlbumPhotos(albumId: Int): AlbumPhotosDto

    suspend fun createAlbum(title: String, currentUserId: Int, privacy: Int): Int

    suspend fun deleteAlbumPhoto(photoId: Int, visitedUserId: Int): Boolean
    //endregion

    //region notifications
    suspend fun getUserNotifications(currentUserId: Int): UserNotificationsDTO

    suspend fun getUserNotificationsCount(currentUserId: Int, ): UserNotificationsCountDto

    suspend fun markUserNotificationsAsViewed(notificationId: Int): NotificationItemsDto
    //endregion

    //region comment
    suspend fun getComments(currentUserId: Int, postId: Int, type: String): List<CommentDetails>

    suspend fun editComment(commentId: Int, comment: String, ): CommentEditionDto

    suspend fun deleteComment(commentId: Int, userId: Int, ): Boolean

    suspend fun addComment(postId : Int, comment: String, userId: Int): CommentDetails
    //endregion

    //region photo
    suspend fun getPhoto(
        photoId: Int,
        userId: Int,
    ): PhotoDto

    suspend fun getPhotosListProfileCover(userId: Int, type: String, ): BaseResponse<List<Photo>>

    suspend fun getPhotoViewProfile(photoId: Int, userId: Int, ) : BaseResponse<UserProfileDto>

    suspend fun deletePhotoProfile(photoId: Int, userId: Int, ) : BaseResponse<ProfilePhotoDeletion>

    suspend fun deleteProfileCover(photoId: Int, userId: Int, ) : BaseResponse<ProfilePhotoDeletion>

//endregion

}