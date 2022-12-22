package com.octopus.socialnetwork.data.remote.service

import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageNotificationDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface CloudMessagingService {

    companion object {
        const val BASE_URL = "https://fcm.googleapis.com"
        private const val CONTENT_TYPE = "application/json"
        private const val key = "AAAAXanVE_U:APA91bE3NBMh5djnkHNAmvH3R6ZGsU9NKc1Uylo8NWDnEHrElYvnW_0BDWT6ZvTB5TLDW8c7HUuNAHY1HqVbBeroWrgjz-00dLxESjtUlKVdtghd-fQNyeU5_eJBYfZqAYojtcEoqmps"
    }

    @POST("fcm/send")
    @Headers("Authorization: key=$key", "Content-Type:$CONTENT_TYPE")
    suspend fun postNotification(
        @Body notification: MessageNotificationDto
    ): Response<ResponseBody>
}