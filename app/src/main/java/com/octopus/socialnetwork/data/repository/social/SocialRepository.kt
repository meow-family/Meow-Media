package com.octopus.socialnetwork.data.repository.social

import androidx.paging.Pager
import com.octopus.socialnetwork.data.local.entity.PostEntity
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
import java.io.File

interface SocialRepository {

    //region user
    suspend fun getUserDetails(visitedUserId: Int): UserDto

    suspend fun getUserFriends(visitedUserId: Int): UserFriendsDto

    suspend fun checkUserFriend(myUserId: Int, userIdWantedToCheck: Int): FriendValidatorDTO?

    suspend fun getUserPosts(visitedUserId: Int, myUserId: Int): UserPostsDto

    suspend fun editUser(
        myUserId: Int, firstName: String, lastName: String,
        email: String, currentPassword: String, newPassword: String
    ): UserDto

    suspend fun addFriend(myUserId: Int, userIdWantedToAdd: Int): FriendValidatorDTO
    suspend fun removeFriend(myUserId: Int, userIdWantedToAdd: Int): FriendValidatorDTO
    suspend fun getFriendRequests(myUserId: Int): FriendRequestsListDTO

    //endregion

    //region post
    suspend fun viewPost(postId: Int, myUserId: Int): PostDto

    suspend fun viewUserPosts(visitedUserId: Int, myUserId: Int): BaseResponse<AllPostDto>

    fun getNewsFeedPager(): Pager<Int, PostEntity>


    suspend fun createPost(
        myUserId: Int,
        posterOwnerId: Int,
        post: String,
        type: String,
        photo: File
    ): PostDto?

    suspend fun deletePost(postId: Int, postOwnerId: Int): PostDto
    suspend fun like(myUserId: Int, contentId: Int, typeContent: String): LikeDto

    suspend fun unlike(myUserId: Int, contentId: Int, typeContent: String): LikeDto

    suspend fun getUserNotifications(myUserId: Int): UserNotificationsDTO

    suspend fun getUserNotificationsCount(myUserId: Int): UserNotificationsCountDto

    suspend fun markUserNotificationsAsViewed(notificationId: Int): NotificationItemsDto

    suspend fun getComments(myUserId: Int, postId: Int, type: String): List<CommentDetails>

    suspend fun editComment(commentId: Int, comment: String): CommentEditionDto

    suspend fun deleteComment(commentId: Int, userId: Int): Boolean

    suspend fun addComment(postId: Int, comment: String, userId: Int): CommentDetails

    suspend fun updatePostLikeStatusLocally(id: Int, isLikedByUser: Boolean, newLikesCount: Int)
    //endregion

    //region photo
    suspend fun getPhoto(
        photoId: Int,
        userId: Int,
    ): PhotoDto

    suspend fun getPhotosListProfileCover(userId: Int, type: String): BaseResponse<List<Photo>>

    suspend fun getPhotoViewProfile(photoId: Int, userId: Int): BaseResponse<UserProfileDto>

    suspend fun deletePhotoProfile(photoId: Int, userId: Int): BaseResponse<ProfilePhotoDeletion>

    suspend fun deleteProfileCover(photoId: Int, userId: Int): BaseResponse<ProfilePhotoDeletion>

    suspend fun addProfilePicture(userID: Int, photo: File): UserDto

    suspend fun addCoverPicture(userID: Int, photo: File): UserDto

    // search
    suspend fun search(
        myUserId: Int,
        query: String
    ): SearchDto

//endregion

}