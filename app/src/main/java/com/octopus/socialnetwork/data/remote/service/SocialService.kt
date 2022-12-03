package com.octopus.socialnetwork.data.remote.service

import com.octopus.socialnetwork.data.remote.response.dto.album.AlbumsDto
import com.octopus.socialnetwork.data.remote.response.dto.album.InfoAlbumDto
import com.octopus.socialnetwork.data.remote.response.dto.album.StateDto
import com.octopus.socialnetwork.data.remote.response.dto.album.album_photos_list.AlbumPhotosDTO
import com.octopus.socialnetwork.data.remote.response.dto.auth.AuthResponse
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
import com.octopus.socialnetwork.data.remote.response.dto.post.PostDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.CheckUserFriendDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserFriendsDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserPostsDTO
import retrofit2.http.*

interface SocialService {
    @POST("user_authenticate")
    suspend fun login(
        @Query("username") username: String,
        @Query("password") password: String,
    ): BaseResponse<AuthResponse>

    @POST("user_add")
    suspend fun signup(
        @Query("firstname") firstName: String,
        @Query("lastname") lastName: String,
        @Query("email") email: String,
        @Query("reemail") reEmail: String,
        @Query("gender") gender: String,
        @Query("birthdate") birthDate: String,
        @Query("username") userName: String,
        @Query("password") password: String,
    ): BaseResponse<AuthResponse>

    @GET("user_details")
    suspend fun getUserDetails(
        @Query("guid") visitedUserId: Int,
    ): BaseResponse<UserDTO>

    @GET("user_friends")
    suspend fun getUserFriends(
        @Query("guid") visitedUserId: Int,
    ): BaseResponse<UserFriendsDTO>

    @GET("wall_list_user")
    suspend fun getUserPosts(
        @Query("uguid") visitedUserId: Int,
        @Query("guid") currentUserId: Int,
    ): BaseResponse<UserPostsDTO>

    @GET("wall_view")
    suspend fun viewPost(
        @Query("post_guid") postId: Int,
        @Query("guid") userId: Int,
    ): BaseResponse<PostDTO>

    @GET("wall_list_user")
    suspend fun viewUserPosts(
        @Query("uguid") visitedUserId: Int,
        @Query("guid") currentUserId: Int,
    ): List<BaseResponse<PostDTO>>

    @GET("wall_list_home")
    suspend fun viewNewsFeed(
        @Query("guid") userId: Int,
    ): List<BaseResponse<PostDTO>>

    // create post without any parameters?
    @FormUrlEncoded
    @POST("wall_add")
    suspend fun createPost(
        @Field("owner_guid") currentUserId: Int,
        @Field("poster_guid") posterOwnerId: Int,
        @Field("post") text: String,
        @Field("type") type: String,
    ): BaseResponse<PostDTO>

    @POST("wall_delete")
    suspend fun deletePost(
        @Query("post_guid") postId: Int,
        @Query("guid") userId: Int,
    ): BaseResponse<PostDTO>

    @POST("like_add")
    suspend fun like(
        @Query("uguid") currentUserId: Int,
        @Query("subject_guid") contentId: Int,
        @Query("type") typeContent: String,
    ): BaseResponse<LikeDTO>

    @POST("unlike_set")
    suspend fun unlike(
        @Query("uguid") currentUserId: Int,
        @Query("subject_guid") contentId: Int,
        @Query("type") typeContent: String,
    ): BaseResponse<LikeDTO>


    @GET("user_is_friend")
    suspend fun checkUserFriend(
        @Query("user_b") receiverUser: Int,
        @Query("user_a") senderUser: Int,
    ): BaseResponse<CheckUserFriendDTO>

    // Notifications
    @GET("notifications_list_user")
    suspend fun getUserNotifications(
        @Query("owner_guid") currentUserId: Int,
        @Query("types") types: String?,
        @Query("offset") offset: Int?,
    ): BaseResponse<UserNotificationsDTO>

    @GET("notifications_count")
    suspend fun getUserNotificationsCount(
        @Query("guid") currentUserId: Int,
        @Query("types") types: String?,
    ): BaseResponse<UserNotificationsCountDTO>

    @GET("notifications_mark_viewed")
    suspend fun markUserNotificationsAsViewed(
        @Query("notification_guid") notificationId: Int,
    ): BaseResponse<NotificationItemsDTO>


    @GET("photos_list_albums")
    suspend fun getAlbumsUser(
        @Query("guid") albumOwnerUserId: Int,
        @Query("uguid") viewerUserId: Int, // TODO: needs confirmation, check before work
    ): BaseResponse<AlbumsDto>

    @GET("photos_list")
    suspend fun getAlbumPhotos(
        @Query("album_guid") albumId: Int,
    ): BaseResponse<AlbumPhotosDTO>


    @POST("photos_album_create")
    suspend fun createAlbum(
        @Path("title") title: String,
        @Query("guid") currentUserId: Int,
        @Field("privacy") privacy: Int,
    ): BaseResponse<InfoAlbumDto>


    @POST("photos_delete")
    suspend fun deleteAlbumPhoto(
        @Path("photoid") photoId: Int,
        @Query("guid") visitedUserId: Int,
    ): BaseResponse<StateDto>


    @GET("message_recent")
    suspend fun getMessagesListRecent(
        @Query("guid") userId: Int
    ): BaseResponse<RecentMessagesDTO>

    @POST("message_add")
    suspend fun sendMessage(
        @Query("from") messageSenderId: Int,
        @Query("to") messageReceiverId: Int,
        @Query("massage") message: String
    ): BaseResponse<SendMessageDTO>

    @POST("message_new")
    suspend fun unreadMessages(
        @Query("from") messageSenderId: Int,
        @Query("to") messageReceiverId: Int,
        @Query("markallread") markAllRead: String
    ): BaseResponse<UnreadMessagesDTO>

    @POST("message_list")
    suspend fun getMessagesList(
        @Query("guid") messageSenderId: Int,
        @Query("to") messageReceiverId: Int,
    ): BaseResponse<MessageListDTO>


    //region comment
    @GET("comments_list")
    suspend fun getCommentsList(
        @Query("uguid") currentUserId: Int,
        @Query("guid") postId: Int,
        @Query("type") type: String,
    ): BaseResponse<CommentDTO>

    @POST("comment_edit")
    suspend fun editComment(
        @Query("guid") commentId: Int,
        @Query("comment") comment: String,
    ): BaseResponse<CommentEditionDTO>

    @POST("comment_delete")
    suspend fun deleteComment(
        @Query("id") commentId: Int,
        @Query("guid") userId: Int,
    ): BaseResponse<Boolean>
    //endregion

    //region photo
    @GET("photos_view")
    suspend fun getPhoto(
        @Query("photo_guid") photoId: Int,
        @Query("uguid") userId: Int,
    ) :BaseResponse<PhotoDTO>

    @GET("photos_list_profile_cover")
    suspend fun getPhotosListProfileCover(
        @Query("guid") userId: Int,
        @Query("type") type: String,
    ): BaseResponse<List<Photo>>

    @GET("photos_view_profile")
    suspend fun getPhotoViewProfile(
      @Query("photo_guid") photoId: Int,
      @Query("uguid") userId: Int,
    ) : BaseResponse<UserProfileDTO>

    @GET("photos_delete_profile")
    suspend fun deletePhotoProfile(
      @Query("photoid") photoId: Int,
      @Query("uguid") userId: Int,
    ) : BaseResponse<ProfilePhotoDeletion>

    @GET("photos_delete_cover")
    suspend fun deleteCoverPhoto(
      @Query("photoid") photoId: Int,
      @Query("uguid") userId: Int,
    ) : BaseResponse<ProfilePhotoDeletion>

//endregion

}