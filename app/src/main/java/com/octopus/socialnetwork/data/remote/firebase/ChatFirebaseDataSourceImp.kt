package com.octopus.socialnetwork.data.remote.firebase


import com.octopus.socialnetwork.data.remote.response.dto.messages.NotificationData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChatFirebaseDataSourceImp @Inject constructor() : ChatFirebaseDataSource {
    override fun onReceiveNotification(): Flow<NotificationData> {
        return FirebaseCloudMessagingService.events
    }

}