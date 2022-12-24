package com.octopus.socialnetwork.data.remote.service

import com.octopus.socialnetwork.BuildConfig.FCM_KEY
import com.octopus.socialnetwork.data.remote.response.dto.messages.MessageNotificationDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface CloudMessagingService {

    @POST("fcm/send")
    @Headers("Authorization: key=$FCM_KEY", "Content-Type:$CONTENT_TYPE")
    suspend fun postNotification(
        @Body notification: MessageNotificationDto
    ): Response<ResponseBody>

    companion object {
        const val BASE_URL = "https://fcm.googleapis.com"
        private const val CONTENT_TYPE = "application/json"
    }
}