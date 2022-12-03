package com.octopus.socialnetwork.data.repository.social

import com.octopus.socialnetwork.data.remote.response.base.BaseResponse
import com.octopus.socialnetwork.data.remote.response.dto.album.AlbumsDto
import com.octopus.socialnetwork.data.remote.response.dto.album.album_photos_list.AlbumPhotosDTO
import com.octopus.socialnetwork.data.remote.response.dto.comment.CommentDTO
import com.octopus.socialnetwork.data.remote.response.dto.comment.edit.CommentEditionDTO
import com.octopus.socialnetwork.data.remote.response.dto.like.LikeDTO
import com.octopus.socialnetwork.data.remote.response.dto.notifications.NotificationItemsDTO
import com.octopus.socialnetwork.data.remote.response.dto.notifications.UserNotificationsCountDTO
import com.octopus.socialnetwork.data.remote.response.dto.notifications.UserNotificationsDTO
import com.octopus.socialnetwork.data.remote.response.dto.photo.delete_photo.ProfilePhotoDeletion
import com.octopus.socialnetwork.data.remote.response.dto.photo.photoDetails.Photo
import com.octopus.socialnetwork.data.remote.response.dto.photo.photoDetails.PhotoDTO
import com.octopus.socialnetwork.data.remote.response.dto.photo.photo_profile.UserProfileDTO
import com.octopus.socialnetwork.data.remote.response.dto.post.PostDTO
import com.octopus.socialnetwork.data.remote.response.dto.post.PostDetailsDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.*
import com.octopus.socialnetwork.data.remote.service.SocialService
import javax.inject.Inject

class SocialRepositoryImpl @Inject constructor(
    private val socialService: SocialService,
) : SocialRepository {

    //region user
    override suspend fun getUserDetails(visitedUserId: Int): UserDTO {
        return socialService.getUserDetails(visitedUserId).result
    }

    override suspend fun getUserFriends(visitedUserId: Int): UserFriendsDTO {
        return socialService.getUserFriends(visitedUserId).result
    }

    override suspend fun checkUserFriend(
        currentUserId: Int,
        userIdWantedToCheck: Int
    ): BaseResponse<CheckUserFriendDTO> {
        return socialService.checkUserFriend(currentUserId, userIdWantedToCheck)
    }

    override suspend fun getUserPosts(visitedUserId: Int, currentUserId: Int): UserPostsDTO {
        return socialService.getUserPosts(visitedUserId, currentUserId).result
    }

    override suspend fun editUser(
        currentUserId: Int,
        firstName: String,
        lastName: String, email: String,
        currentPassword: String,
        newPassword: String
    ): UserEditDTO {
        return socialService.editUser(
            currentUserId,
            firstName,
            lastName,
            email,
            currentPassword,
            newPassword
        ).result
    }

    //endregion
    //region post
    override suspend fun viewPost(postId: Int, userId: Int): BaseResponse<PostDTO> {
        return socialService.viewPost(
            postId,
            userId,
        )
    }

    override suspend fun viewUserPosts(
        visitedUserId: Int,
        currentUserId: Int
    ): List<BaseResponse<PostDTO>> {
        return socialService.viewUserPosts(
            visitedUserId,
            currentUserId,
        )
    }

    override suspend fun viewNewsFeed(userId: Int): List<BaseResponse<PostDTO>> {
        return socialService.viewNewsFeed(
            userId,
        )
    }

    override suspend fun createPost(
        currentUserId: Int,
        posterOwnerId: Int,
        post: String,
        type: String
    ): BaseResponse<PostDTO> {
        return socialService.createPost(currentUserId, posterOwnerId, post, type)
    }

    override suspend fun deletePost(postId: Int, userId: Int): BaseResponse<PostDTO> {
        return socialService.deletePost(
            postId,
            userId,
        )
    }

    override suspend fun like(
        currentUserId: Int,
        contentId: Int,
        typeContent: String
    ): BaseResponse<LikeDTO> {
        return socialService.like(currentUserId, contentId, typeContent)
    }

    override suspend fun unlike(
        currentUserId: Int,
        contentId: Int,
        typeContent: String
    ): BaseResponse<LikeDTO> {
        return socialService.unlike(currentUserId, contentId, typeContent)
    }
//endregion


    //region albums
    override suspend fun getAlbumsUser(albumOwnerUserId: Int, viewerUserId: Int): AlbumsDto {
        return socialService.getAlbumsUser(albumOwnerUserId, viewerUserId).result
    }

    override suspend fun getAlbumPhotos(albumId: Int): AlbumPhotosDTO {
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
        types: String?,
        offset: Int?
    ): UserNotificationsDTO {
        return socialService.getUserNotifications(currentUserId, types ?: "", offset ?: 1).result
    }

    override suspend fun getUserNotificationsCount(
        currentUserId: Int,
        types: String?
    ): UserNotificationsCountDTO {
        return socialService.getUserNotificationsCount(currentUserId, types ?: "").result
    }

    override suspend fun markUserNotificationsAsViewed(notificationId: Int): NotificationItemsDTO {
        return socialService.markUserNotificationsAsViewed(notificationId).result
    }

//endregion

    //region comment

    override suspend fun getComments(
        currentUserId: Int,
        postId: Int,
        type: String
    ): BaseResponse<CommentDTO> {
        return socialService.getCommentsList(currentUserId, postId, type)
    }

    override suspend fun editComment(
        commentId: Int,
        comment: String
    ): BaseResponse<CommentEditionDTO> {
        return socialService.editComment(commentId, comment)
    }

    override suspend fun deleteComment(commentId: Int, userId: Int): BaseResponse<Boolean> {
        return socialService.deleteComment(commentId, userId)
    }

    override suspend fun getPhoto(photoId: Int, userId: Int): BaseResponse<PhotoDTO> {
        return socialService.getPhoto(photoId, userId)
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
    ): BaseResponse<UserProfileDTO> {
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