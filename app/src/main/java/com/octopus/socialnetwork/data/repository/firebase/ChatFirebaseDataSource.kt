package com.octopus.socialnetwork.data.repository.firebase

import com.octopus.socialnetwork.data.remote.response.dto.messages.NotificationData
import kotlinx.coroutines.flow.Flow

interface ChatFirebaseDataSource {

    fun onReceiveNotification(): Flow<NotificationData>
}