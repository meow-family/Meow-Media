package com.octopus.socialnetwork.data.repository.social

import androidx.paging.Pager
import androidx.paging.PagingData
import com.octopus.socialnetwork.data.local.entity.PostEntity
import com.octopus.socialnetwork.data.local.entity.UserEntity
import com.octopus.socialnetwork.data.remote.response.base.BaseResponse
import com.octopus.socialnetwork.data.remote.response.dto.comment.CommentDto
import com.octopus.socialnetwork.data.remote.response.dto.comment.CommentEditResponse
import com.octopus.socialnetwork.data.remote.response.dto.like.LikeResponse
import com.octopus.socialnetwork.data.remote.response.dto.notifications.NotificationItemsDto
import com.octopus.socialnetwork.data.remote.response.dto.notifications.NotificationsCountDto
import com.octopus.socialnetwork.data.remote.response.dto.notifications.NotificationsResponse
import com.octopus.socialnetwork.data.remote.response.dto.photo.PhotoDto
import com.octopus.socialnetwork.data.remote.response.dto.photo.ProfilePhotoResponse
import com.octopus.socialnetwork.data.remote.response.dto.photo.UserProfileDto
import com.octopus.socialnetwork.data.remote.response.dto.post.PostResponse
import com.octopus.socialnetwork.data.remote.response.dto.post.PostDto
import com.octopus.socialnetwork.data.remote.response.dto.search.SearchDto
import com.octopus.socialnetwork.data.remote.response.dto.user.friend_requests.FriendValidatorResponse
import com.octopus.socialnetwork.data.remote.response.dto.user.UserDto
import com.octopus.socialnetwork.data.remote.response.dto.user.FriendsDto
import com.octopus.socialnetwork.data.remote.response.dto.user.PostsDto
import com.octopus.socialnetwork.data.remote.response.dto.user.friend_requests.FriendRequestsResponse
import kotlinx.coroutines.flow.Flow
import java.io.File

interface SocialRepository {

    //region user
    suspend fun getUserDetails(visitedUserId: Int): UserDto

    suspend fun insertProfileDetails(userId: Int)

    suspend fun getProfileDetails(): Flow<UserEntity>

    suspend fun getFriends(visitedUserId: Int): FriendsDto

    suspend fun checkUserFriend(myUserId: Int, userIdWantedToCheck: Int): FriendValidatorResponse?

    suspend fun getUserPosts(visitedUserId: Int, myUserId: Int): PostsDto

    suspend fun editUser(
        myUserId: Int, firstName: String, lastName: String,
        email: String, currentPassword: String, newPassword: String
    ): UserDto

    suspend fun addFriend(myUserId: Int, userIdWantedToAdd: Int): FriendValidatorResponse
    suspend fun removeFriend(myUserId: Int, userIdWantedToAdd: Int): FriendValidatorResponse
    suspend fun getFriendRequests(myUserId: Int): FriendRequestsResponse

    //endregion

    //region post
    suspend fun viewPost(postId: Int, myUserId: Int): PostDto

    fun getPostDetails(postId: Int): PostEntity

    suspend fun viewUserPosts(visitedUserId: Int, myUserId: Int): BaseResponse<PostResponse>

    fun getNewsFeedPager(): Flow<PagingData<PostEntity>>


    suspend fun createPost(
        myUserId: Int,
        posterOwnerId: Int,
        post: String,
        type: String,
        photo: File
    ): PostDto?

    suspend fun deletePost(postId: Int, postOwnerId: Int): PostDto
    suspend fun like(myUserId: Int, contentId: Int, typeContent: String): LikeResponse

    suspend fun unlike(myUserId: Int, contentId: Int, typeContent: String): LikeResponse

    suspend fun getNotifications(myUserId: Int): NotificationsResponse
    suspend fun getNotificationsPager(myUserId: Int): Pager<Int, NotificationItemsDto>

    suspend fun getNotificationsCount(myUserId: Int): NotificationsCountDto

    suspend fun markUserNotificationsAsViewed(notificationId: Int): NotificationItemsDto

    suspend fun getCommentsPager(postId: Int): Pager<Int, CommentDto>

    suspend fun editComment(commentId: Int, comment: String): CommentEditResponse

    suspend fun deleteComment(commentId: Int, userId: Int): Boolean

    suspend fun addComment(postId: Int, comment: String, userId: Int): CommentDto

    suspend fun updatePostLikeStatusLocally(id: Int, isLikedByUser: Boolean, newLikesCount: Int)
    //endregion

    //region photo
    suspend fun getPhoto(photoId: Int, userId: Int): PhotoDto

    suspend fun getPhotosListProfileCover(userId: Int, type: String): BaseResponse<List<PhotoDto>>

    suspend fun getPhotoViewProfile(photoId: Int, userId: Int): BaseResponse<UserProfileDto>

    suspend fun deletePhotoProfile(photoId: Int, userId: Int): BaseResponse<ProfilePhotoResponse>

    suspend fun deleteProfileCover(photoId: Int, userId: Int): BaseResponse<ProfilePhotoResponse>

    suspend fun addProfilePicture(userID: Int, photo: File): UserDto

    suspend fun addCoverPicture(userID: Int, photo: File): UserDto

    // search
    suspend fun search(myUserId: Int, query: String): SearchDto

    suspend fun insertPosts(posts: List<PostEntity>)

//endregion

}