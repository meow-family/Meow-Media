package com.octopus.socialnetwork.domain.usecase.messages

import com.google.firebase.messaging.FirebaseMessaging
import com.octopus.socialnetwork.data.remote.response.dto.messages.NotificationData
import com.octopus.socialnetwork.data.repository.firebase.ChatFirebaseDataSource
import com.octopus.socialnetwork.domain.model.messages.MessageNotification
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ReceiveMessageUseCase @Inject constructor(
    private val chatFirebaseDataSource: ChatFirebaseDataSource
) {
    operator fun invoke(): Flow<NotificationData>  {
        return chatFirebaseDataSource.onReceiveNotification()
    }
}