package com.octopus.socialnetwork.data.repository.messaging


import com.octopus.socialnetwork.data.remote.response.dto.messages.NotificationData
import com.octopus.socialnetwork.data.remote.service.FirebaseCloudMessagingService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChatFirebaseDataSourceImp @Inject constructor() : ChatFirebaseDataSource {
    override fun onReceiveNotification(): Flow<NotificationData> {
        return FirebaseCloudMessagingService.events
    }

}