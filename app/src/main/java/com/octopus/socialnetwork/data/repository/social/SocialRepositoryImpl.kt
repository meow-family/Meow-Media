package com.octopus.socialnetwork.data.repository.social

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.octopus.socialnetwork.data.local.database.SocialDatabase
import com.octopus.socialnetwork.data.local.entity.PostEntity
import com.octopus.socialnetwork.data.paging.PostsRemoteMediator
import android.util.Log
import com.octopus.socialnetwork.BuildConfig
import com.octopus.socialnetwork.data.remote.response.base.BaseResponse

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
import com.octopus.socialnetwork.data.remote.response.dto.search.SearchDto
import com.octopus.socialnetwork.data.remote.response.dto.user.FriendValidatorDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserDto
import com.octopus.socialnetwork.data.remote.response.dto.user.UserFriendsDto
import com.octopus.socialnetwork.data.remote.response.dto.user.UserPostsDto
import com.octopus.socialnetwork.data.remote.response.dto.user.friend_requests.FriendRequestsListDTO
import com.octopus.socialnetwork.data.remote.service.SocialService
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

@ExperimentalPagingApi
class SocialRepositoryImpl @Inject constructor(
    private val socialService: SocialService,
    private val socialDatabase: SocialDatabase,
    private val postsRemoteMediator: PostsRemoteMediator,
) : SocialRepository {

    //region user
    override suspend fun getUserDetails(visitedUserId: Int): UserDto {
        return socialService.getUserDetails(visitedUserId).result
    }

    override suspend fun getUserFriends(visitedUserId: Int): UserFriendsDto {
        return socialService.getUserFriends(visitedUserId).result
    }

    override suspend fun checkUserFriend(
        myUserId: Int, userIdWantedToCheck: Int
    ): FriendValidatorDTO? {
        return socialService.checkUserFriend(myUserId, userIdWantedToCheck).result
    }

    override suspend fun getUserPosts(visitedUserId: Int, myUserId: Int): UserPostsDto {
        return socialService.getUserPosts(visitedUserId, myUserId).result
    }

    override suspend fun editUser(
        myUserId: Int,
        firstName: String,
        lastName: String,
        email: String,
        currentPassword: String,
        newPassword: String
    ): UserDto {
        return socialService.editUser(
            myUserId,
            firstName,
            lastName,
            email,
            currentPassword,
            newPassword
        ).result
    }

    override suspend fun addFriend(myUserId: Int, userIdWantedToAdd: Int): FriendValidatorDTO {
        return socialService.addFriend(myUserId, userIdWantedToAdd).result
    }

    override suspend fun removeFriend(
        myUserId: Int,
        userIdWantedToAdd: Int
    ): FriendValidatorDTO {
        return socialService.removeFriend(myUserId, userIdWantedToAdd).result
    }

    override suspend fun getFriendRequests(myUserId: Int): FriendRequestsListDTO {
        return socialService.getFriendRequests(myUserId).result
    }

    //endregion
    //region post
    override suspend fun viewPost(postId: Int, myUserId: Int): PostDto {
        return socialService.viewPost(postId, myUserId).result
    }

    override suspend fun viewUserPosts(
        visitedUserId: Int,
        myUserId: Int
    ): BaseResponse<AllPostDto> {
        return socialService.viewUserPosts(
            visitedUserId,
            myUserId,
        )
    }




    override fun getNewsFeedPager(): Pager<Int, PostEntity> {
        return Pager(
            config = PagingConfig(10),
            remoteMediator = postsRemoteMediator,
            pagingSourceFactory = { socialDatabase.postsDao().getAllPosts() }
        )
    }

    override suspend fun createPost(
        myUserId: Int,
        posterOwnerId: Int,
        post: String,
        type: String,
        photo: File
    ): PostDto {
        Log.i("MEOW",photo.extension + " is the extension! ${"image/${photo.extension}".toMediaTypeOrNull()}")
        val photoExtension = if (photo.extension=="jpg") "jpeg" else photo.extension
        val requestFile = photo.asRequestBody("image/$photoExtension".toMediaType())
        val builder: MultipartBody.Builder = MultipartBody.Builder().setType(MultipartBody.FORM)

        val requestBody = builder.addFormDataPart("api_key_token", BuildConfig.API_KEY) // whatever data you will pass to the the request body
            .addFormDataPart("owner_guid", myUserId.toString())
            .addFormDataPart("poster_guid",posterOwnerId.toString())
            .addFormDataPart("type",type)
            .addFormDataPart("post",post)
            .addFormDataPart("privacy","2")
            .addFormDataPart("ossn_photo",photo.name,requestFile).build()

        return socialService.createPost(requestBody).result
    }

    override suspend fun deletePost(postId: Int, postOwnerId: Int): PostDto {
        return socialService.deletePost(
            postId,
            postOwnerId,
        ).result
    }

    override suspend fun like(
        myUserId: Int,
        contentId: Int,
        typeContent: String
    ): LikeDto {
        return socialService.like(myUserId, contentId, typeContent).result
    }

    override suspend fun unlike(
        myUserId: Int,
        contentId: Int,
        typeContent: String
    ): LikeDto {
        return socialService.unlike(myUserId, contentId, typeContent).result
    }

    override suspend fun getUserNotifications(
        myUserId: Int,
    ): UserNotificationsDTO {
        return socialService.getUserNotifications(myUserId).result
    }

    override suspend fun getUserNotificationsCount(
        myUserId: Int,
    ): UserNotificationsCountDto {
        return socialService.getUserNotificationsCount(myUserId).result
    }

    override suspend fun markUserNotificationsAsViewed(notificationId: Int): NotificationItemsDto {
        return socialService.markUserNotificationsAsViewed(notificationId).result
    }



    override suspend fun getComments(
        myUserId: Int,
        postId: Int,
        type: String
    ): List<CommentDetails> {
        return socialService.getCommentsList(myUserId, postId, type).result.comments
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

    override suspend fun updatePostLikeStatusLocally(id: Int, isLikedByUser: Boolean, newLikesCount: Int) {
        Log.d("MALT", "WORKED UNTIL DAO: $id, $isLikedByUser")
        socialDatabase.postsDao().updatePostLikeStatus(id, isLikedByUser, newLikesCount)
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

    override suspend fun addProfilePicture(userID: Int, photo: File): UserDto {
        val photoExtension = if (photo.extension=="jpg") "jpeg" else photo.extension
        val requestFile = photo.asRequestBody("image/$photoExtension".toMediaType())
        val builder: MultipartBody.Builder = MultipartBody.Builder().setType(MultipartBody.FORM)

        val requestBody = builder.addFormDataPart("api_key_token", BuildConfig.API_KEY)
            .addFormDataPart("guid", userID.toString())
            .addFormDataPart("userphoto",photo.name,requestFile).build()

        return socialService.addProfilePicture(requestBody).result
    }

    override suspend fun addCoverPicture(userID: Int, photo: File): UserDto {
        val photoExtension = if (photo.extension=="jpg") "jpeg" else photo.extension
        val requestFile = photo.asRequestBody("image/$photoExtension".toMediaType())
        val builder: MultipartBody.Builder = MultipartBody.Builder().setType(MultipartBody.FORM)

        val requestBody = builder.addFormDataPart("api_key_token", BuildConfig.API_KEY)
            .addFormDataPart("guid", userID.toString())
            .addFormDataPart("userphoto",photo.name,requestFile).build()

        return socialService.addCoverPicture(requestBody).result
    }

    override suspend fun search(
        myUserId: Int,
        query: String
    ): SearchDto {
        return socialService.search(
            myUserId,
            query
        ).result
    }
    //endregion

}