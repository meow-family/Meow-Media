package com.octopus.socialnetwork.data.remote.service

import com.octopus.socialnetwork.data.remote.response.dto.auth.AuthResponse
import com.octopus.socialnetwork.data.remote.response.dto.base.BaseResponse
import com.octopus.socialnetwork.data.remote.response.dto.notifications.NotificationItemsDTO
import com.octopus.socialnetwork.data.remote.response.dto.notifications.UserNotificationsCountDTO
import com.octopus.socialnetwork.data.remote.response.dto.notifications.UserNotificationsDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserDetailsDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserFriendsDTO
import com.octopus.socialnetwork.data.remote.response.dto.user.UserPostsDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

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

    // Notifications
    @GET("notifications_list_user")
    suspend fun getUserNotifications(
        @Query("owner_guid") currentUserId: Int,
        //@Query("types") types: String,
        //@Query("offset") offset: Int,
    ): BaseResponse<UserNotificationsDTO>

    @GET("notifications_count")
    suspend fun getUserNotificationsCount(
        @Query("guid") currentUserId: Int,
        //@Query("types") types: String,
    ): BaseResponse<UserNotificationsCountDTO>

    @GET("notifications_mark_viewed")
    suspend fun markUserNotificationsAsViewed(
        @Query("notification_guid") notificationId: Int,
    ): BaseResponse<NotificationItemsDTO>

}
