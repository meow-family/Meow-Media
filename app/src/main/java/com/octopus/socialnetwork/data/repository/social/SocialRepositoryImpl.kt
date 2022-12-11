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
import com.octopus.socialnetwork.data.remote.response.dto.user.CheckUserFriendDto
import com.octopus.socialnetwork.data.remote.response.dto.user.UserDto
import com.octopus.socialnetwork.data.remote.response.dto.user.UserFriendsDto
import com.octopus.socialnetwork.data.remote.response.dto.user.UserPostsDto
import com.octopus.socialnetwork.data.remote.service.SocialService
import javax.inject.Inject

class SocialRepositoryImpl @Inject constructor(
    private val socialService: SocialService,
) : SocialRepository {

    //region user
    override suspend fun getUserDetails(visitedUserId: Int): UserDto {
        return socialService.getUserDetails(visitedUserId).result
    }

    override suspend fun getUserFriends(visitedUserId: Int): UserFriendsDto {
        return socialService.getUserFriends(visitedUserId).result
    }

    override suspend fun checkUserFriend(
        currentUserId: Int,
        userIdWantedToCheck: Int
    ): CheckUserFriendDto {
        return socialService.checkUserFriend(currentUserId, userIdWantedToCheck).result
    }

    override suspend fun getUserPosts(visitedUserId: Int, currentUserId: Int): UserPostsDto {
        return socialService.getUserPosts(visitedUserId, currentUserId).result
    }

    override suspend fun editUser(
        currentUserId: Int,
        firstName: String,
        lastName: String,
        email: String,
        currentPassword: String,
        newPassword: String
    ): UserDto {
        return socialService.editUser(
            currentUserId,
            firstName,
            lastName,
            email,
            currentPassword,
            newPassword
        ).result
    }

    override suspend fun addFriend(currentUserId: Int, userIdWantedToAdd: Int): CheckUserFriendDto {
        return socialService.addFriend(currentUserId, userIdWantedToAdd).result
    }

    override suspend fun removeFriend(
        currentUserId: Int,
        userIdWantedToAdd: Int
    ): CheckUserFriendDto {
        return socialService.removeFriend(currentUserId, userIdWantedToAdd).result
    }

    //endregion
    //region post
    override suspend fun viewPost(postId: Int, currentUserId: Int): PostDto {
        return socialService.viewPost(postId, currentUserId).result
    }

    override suspend fun viewUserPosts(
        visitedUserId: Int,
        currentUserId: Int
    ): BaseResponse<AllPostDto> {
        return socialService.viewUserPosts(
            visitedUserId,
            currentUserId,
        )
    }

    override suspend fun viewNewsFeed(currentUserId: Int): List<PostDto> {
        return socialService.viewNewsFeed(currentUserId).result.posts
    }

    override suspend fun createPost(
        currentUserId: Int,
        posterOwnerId: Int,
        post: String,
        type: String
    ): PostDto {
        return socialService.createPost(currentUserId, posterOwnerId, post, type).result
    }

    override suspend fun deletePost(postId: Int, postOwnerId: Int): PostDto {
        return socialService.deletePost(
            postId,
            postOwnerId,
        ).result
    }

    override suspend fun like(
        currentUserId: Int,
        contentId: Int,
        typeContent: String
    ): LikeDto {
        return socialService.like(currentUserId, contentId, typeContent).result
    }

    override suspend fun unlike(
        currentUserId: Int,
        contentId: Int,
        typeContent: String
    ): LikeDto {
        return socialService.unlike(currentUserId, contentId, typeContent).result
    }
//endregion


    //region albums
    override suspend fun getAlbumsUser(albumOwnerUserId: Int, viewerUserId: Int): AlbumsDto {
        return socialService.getAlbumsUser(albumOwnerUserId, viewerUserId).result
    }

    override suspend fun getAlbumPhotos(albumId: Int): AlbumPhotosDto {
        return socialService.getAlbumPhotos(albumId).result
    }

    override suspend fun createAlbum(
        title: String,
        currentUserId: Int,
        privacy: Int
    ): Int {
        return socialService.createAlbum(title, currentUserId, privacy).result.albumId ?: 0
    }

    override suspend fun deleteAlbumPhoto(
        photoId: Int,
        visitedUserId: Int
    ): Boolean {
        return socialService.deleteAlbumPhoto(photoId, visitedUserId).result.status ?: false
    }
//endregion

    //region notifications
    override suspend fun getUserNotifications(
        currentUserId: Int,
        types: String,
        offset: Int
    ): UserNotificationsDTO {
        return socialService.getUserNotifications(currentUserId, types , offset).result
    }

    override suspend fun getUserNotificationsCount(
        currentUserId: Int,
    ): UserNotificationsCountDto {
        return socialService.getUserNotificationsCount(currentUserId).result
    }

    override suspend fun markUserNotificationsAsViewed(notificationId: Int): NotificationItemsDto {
        return socialService.markUserNotificationsAsViewed(notificationId).result
    }

//endregion

    //region comment

    override suspend fun getComments(
        currentUserId: Int,
        postId: Int,
        type: String
    ): List<CommentDetails> {
        return socialService.getCommentsList(currentUserId, postId, type).result.comments
    }

    override suspend fun editComment(
        commentId: Int,
        comment: String
    ): CommentEditionDto {
        return socialService.editComment(commentId, comment).result
    }

    override suspend fun deleteComment(commentId: Int, userId: Int): Boolean {
        return socialService.deleteComment(commentId, userId).result
    }


    override suspend fun getPhoto(photoId: Int, userId: Int): PhotoDto {
        return socialService.getPhoto(photoId, userId).result
    }

    override suspend fun addComment(postId: Int, comment: String, userId: Int): CommentDetails {
        return socialService.addComment(postId, comment, userId).result
    }

    override suspend fun getPhotosListProfileCover(
        userId: Int,
        type: String
    ): BaseResponse<List<Photo>> {
        return socialService.getPhotosListProfileCover(userId, type)
    }

    override suspend fun getPhotoViewProfile(
        photoId: Int,
        userId: Int
    ): BaseResponse<UserProfileDto> {
        return socialService.getPhotoViewProfile(photoId, userId)
    }

    override suspend fun deletePhotoProfile(
        photoId: Int,
        userId: Int
    ): BaseResponse<ProfilePhotoDeletion> {
        return socialService.deleteCoverPhoto(photoId, userId)
    }

    override suspend fun deleteProfileCover(
        photoId: Int,
        userId: Int
    ): BaseResponse<ProfilePhotoDeletion> {
        return socialService.deleteCoverPhoto(photoId, userId)
    }

    //endregion

}