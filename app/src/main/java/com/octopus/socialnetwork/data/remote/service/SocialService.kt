package com.octopus.socialnetwork.data.remote.service

import com.octopus.socialnetwork.data.remote.response.dto.album.AlbumsDto
import com.octopus.socialnetwork.data.remote.response.dto.album.InfoAlbumDto
import com.octopus.socialnetwork.data.remote.response.dto.album.StateDto
import com.octopus.socialnetwork.data.remote.response.dto.album.album_photos_list.AlbumPhotosDTO
import com.octopus.socialnetwork.data.remote.response.dto.auth.AuthResponse
import com.octopus.socialnetwork.data.remote.response.dto.base.BaseResponse
import com.octopus.socialnetwork.data.remote.response.dto.like.LikeDTO
import com.octopus.socialnetwork.data.remote.response.dto.post.PostDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.CheckUserFriendDTO
import com.octopus.socialnetwork.data.remote.response.dto.messages.list_messages.MessageListDTO
import com.octopus.socialnetwork.data.remote.response.dto.messages.message_send.SendMessageDTO
import com.octopus.socialnetwork.data.remote.response.dto.messages.recent_messages.RecentMessagesDTO
import com.octopus.socialnetwork.data.remote.response.dto.messages.unread_message.UnreadMessagesDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserDetailsDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserFriendsDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserPostsDTO
import retrofit2.Response
import retrofit2.http.*

interface SocialService {
    @POST("user_authenticate")
    suspend fun login(
        @Query("username") username: String,
        @Query("password") password: String,
    ): Response<AuthResponse>

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
    ): Response<AuthResponse>

    @GET("user_details")
    suspend fun getUserDetails(
        @Query("guid") visitedUserId: Int,
    ): BaseResponse<UserDetailsDTO>

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
        @Query("uguid") ownerId: Int,
        @Query("guid") viewerId: Int,
    ): List<BaseResponse<PostDTO>>

    @GET("wall_list_home")
    suspend fun viewNewsFeed(
        @Query("guid") userId: Int,
    ): List<BaseResponse<PostDTO>>

    @POST("wall_add")
    suspend fun createPost(): BaseResponse<PostDTO>

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
        @Query("user_b") currentUserId: Int,
        @Query("user_a") userIdWantedToCheck: Int,
    ): BaseResponse<CheckUserFriendDTO>


    @GET("photos_list_albums")
    suspend fun getAlbumsUser(
        @Query("guid") ownerAlbumsUserId: Int,
        @Query("uguid") visitedUserId: Int,
    ): BaseResponse<AlbumsDto>

    @GET("photos_list")
    suspend fun getAlbumPhotos(
        @Query("album_guid") albumId: Int,
    ): BaseResponse<AlbumPhotosDTO>


    @POST("photos_album_create")
    suspend fun createAlbum(
        @Path("title") title: String,
        @Query("guid") currentUserId: Int,
        @Field("privacy") privacy: Int
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



}