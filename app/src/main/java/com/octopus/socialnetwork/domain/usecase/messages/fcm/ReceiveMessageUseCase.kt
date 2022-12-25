package com.octopus.socialnetwork.domain.usecase.messages.fcm

import com.octopus.socialnetwork.data.remote.response.dto.messages.NotificationData
import com.octopus.socialnetwork.data.remote.firebase.ChatFirebaseDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReceiveMessageUseCase @Inject constructor(
    private val chatFirebaseDataSource: ChatFirebaseDataSource
) {
    operator fun invoke(): Flow<NotificationData>  {
        return chatFirebaseDataSource.onReceiveNotification()
    }
}