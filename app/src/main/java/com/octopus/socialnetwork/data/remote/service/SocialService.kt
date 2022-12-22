package com.octopus.socialnetwork.data.remote.service

import android.util.Xml.Encoding
import com.octopus.socialnetwork.data.remote.response.base.BaseResponse
import com.octopus.socialnetwork.data.remote.response.dto.auth.AuthResponse
import com.octopus.socialnetwork.data.remote.response.dto.auth.RegisterDto
import com.octopus.socialnetwork.data.remote.response.dto.comment.CommentDetails
import com.octopus.socialnetwork.data.remote.response.dto.comment.CommentDto
import com.octopus.socialnetwork.data.remote.response.dto.comment.CommentEditionDto
import com.octopus.socialnetwork.data.remote.response.dto.like.LikeDto
import com.octopus.socialnetwork.data.remote.response.dto.messages.*
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
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*
import java.io.File
import java.lang.annotation.RetentionPolicy

interface SocialService {
    @POST("user_authenticate")
    suspend fun login(
        @Query("username") username: String,
        @Query("password") password: String,
    ): BaseResponse<AuthResponse>

    @POST("user_add")
    suspend fun register(
        @Query("firstname") firstName: String,
        @Query("lastname") lastName: String,
        @Query("email") email: String,
        @Query("reemail") reEmail: String,
        @Query("gender") gender: String,
        @Query("birthdate") birthDate: String,
        @Query("username") userName: String,
        @Query("password") password: String,
    ): BaseResponse<RegisterDto>

    @GET("user_details")
    suspend fun getUserDetails(
        @Query("guid") visitedUserId: Int,
    ): BaseResponse<UserDto>

    @GET("user_friends")
    suspend fun getUserFriends(
        @Query("guid") visitedUserId: Int,
    ): BaseResponse<UserFriendsDto>

    @GET("wall_list_user")
    suspend fun getUserPosts(
        @Query("uguid") visitedUserId: Int,
        @Query("guid") currentUserId: Int,
    ): BaseResponse<UserPostsDto>

    @POST("user_add_friend")
    suspend fun addFriend(
        @Query("user_a") senderUser: Int,
        @Query("user_b") receiverUser: Int
    ): BaseResponse<FriendValidatorDTO>

    @POST("user_remove_friend")
    suspend fun removeFriend(
        @Query("user_a") senderUser: Int,
        @Query("user_b") receiverUser: Int
    ): BaseResponse<FriendValidatorDTO>

    ///////////////////////////////////////////////////
    @POST("user_edit")
    suspend fun editUser(
        @Query("guid") currentUserId: Int,
        @Query("new_first_name") firstName: String,
        @Query("new_last_name") lastName: String,
        @Query("new_email") email: String,
        @Query("current_password") currentPassword: String,
        @Query("new_password") newPassword: String,
    ): BaseResponse<UserDto>

    @GET("user_friend_requests")
    suspend fun getFriendRequests(
        @Query("guid") currentUserId: Int,
    ): BaseResponse<FriendRequestsListDTO>

    ///////////////////////////////////////////////////////

    @GET("wall_view")
    suspend fun viewPost(
        @Query("post_guid") postId: Int,
        @Query("guid") currentUserId: Int,
    ): BaseResponse<PostDto>

    @GET("wall_list_user")
    suspend fun viewUserPosts(
        @Query("uguid") visitedUserId: Int,
        @Query("guid") currentUserId: Int,
    ): BaseResponse<AllPostDto>

    @GET("wall_list_home")
    suspend fun viewNewsFeed(
        @Query("guid") currentUserId: Int,
    ): BaseResponse<AllPostDto>

    // create post without any parameters?

    @POST("wall_add")
    suspend fun createPost(
         @Body body: RequestBody
    ): BaseResponse<PostDto>

    @POST("wall_delete")
    suspend fun deletePost(
        @Query("post_guid") postId: Int,
        @Query("guid") posterOwnerId: Int,
    ): BaseResponse<PostDto>

    @POST("like_add")
    suspend fun like(
        @Query("uguid") currentUserId: Int,
        @Query("subject_guid") contentId: Int,
        @Query("type") typeContent: String,
    ): BaseResponse<LikeDto>

    @POST("unlike_set")
    suspend fun unlike(
        @Query("uguid") currentUserId: Int,
        @Query("subject_guid") contentId: Int,
        @Query("type") typeContent: String,
    ): BaseResponse<LikeDto>


    @GET("user_is_friend")
    suspend fun checkUserFriend(
        @Query("user_a") senderUser: Int,
        @Query("user_b") receiverUser: Int,
    ): BaseResponse<FriendValidatorDTO?>

    // Notifications
    @GET("notifications_list_user")
    suspend fun getUserNotifications(
        @Query("owner_guid") currentUserId: Int,
    ): BaseResponse<UserNotificationsDTO>

    @GET("notifications_count")
    suspend fun getUserNotificationsCount(
        @Query("guid") currentUserId: Int,
    ): BaseResponse<UserNotificationsCountDto>

    @GET("notifications_mark_viewed")
    suspend fun markUserNotificationsAsViewed(
        @Query("notification_guid") notificationId: Int,
    ): BaseResponse<NotificationItemsDto>

    @GET("message_recent")
    suspend fun getMessagesListRecent(
        @Query("guid") userId: Int
    ): BaseResponse<MessageListDto>

    @POST("message_add")
    suspend fun sendMessage(
        @Query("from") messageSenderId: Int,
        @Query("to") messageReceiverId: Int,
        @Query("message") message: String
    ): BaseResponse<MessageDto>

    @POST("message_new")
    suspend fun unreadMessages(
        @Query("from") messageSenderId: Int,
        @Query("to") messageReceiverId: Int,
        @Query("markallread") markAllRead: Int
    ): BaseResponse<MessageListDto>

    @POST("message_list")
    suspend fun getMessagesList(
        @Query("guid") currentUserId: Int,
        @Query("to") otherUserId: Int,
    ): BaseResponse<MessageListDto>


    //region comment
    @GET("comments_list")
    suspend fun getCommentsList(
        @Query("uguid") currentUserId: Int,
        @Query("guid") postId: Int,
        @Query("type") type: String,
    ): BaseResponse<CommentDto>

    @POST("comment_edit")
    suspend fun editComment(
        @Query("guid") commentId: Int,
        @Query("comment") comment: String,
    ): BaseResponse<CommentEditionDto>

    @POST("comment_delete")
    suspend fun deleteComment(
        @Query("id") commentId: Int,
        @Query("guid") userId: Int,
    ): BaseResponse<Boolean>

    @POST("comment_add")
    suspend fun addComment(
        @Query("subject_guid") postId: Int,
        @Query("comment") comment: String,
        @Query("uguid") userId: Int
    ): BaseResponse<CommentDetails>
    //endregion

    //region photo
    @GET("photos_view")
    suspend fun getPhoto(
        @Query("photo_guid") photoId: Int,
        @Query("uguid") userId: Int,
    ): BaseResponse<PhotoDto>

    @GET("photos_list_profile_cover")
    suspend fun getPhotosListProfileCover(
        @Query("guid") userId: Int,
        @Query("type") type: String,
    ): BaseResponse<List<Photo>>

    @GET("photos_view_profile")
    suspend fun getPhotoViewProfile(
        @Query("photo_guid") photoId: Int,
        @Query("uguid") userId: Int,
    ): BaseResponse<UserProfileDto>

    @GET("photos_delete_profile")
    suspend fun deletePhotoProfile(
        @Query("photoid") photoId: Int,
        @Query("uguid") userId: Int,
    ): BaseResponse<ProfilePhotoDeletion>

    @GET("photos_delete_cover")
    suspend fun deleteCoverPhoto(
        @Query("photoid") photoId: Int,
        @Query("uguid") userId: Int,
    ): BaseResponse<ProfilePhotoDeletion>

    // search
    @GET("my_custom_end_point")
    suspend fun search(
        @Query("guid") currentUserId: Int,
        @Query("keyword") query: String,
    ): BaseResponse<SearchDto>
//endregion
}