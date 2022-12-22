package com.octopus.socialnetwork.data.remote.service

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.octopus.socialnetwork.data.remote.response.dto.messages.NotificationData
import com.octopus.socialnetwork.data.remote.response.dto.messages.NotificationKeys
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch


class FirebaseCloudMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        message.data.let { data ->
            if (data.isNotEmpty()) {
                val id = (data[NotificationKeys.ID_KEY]?.toInt() ?: 0)
                val friendId = (data[NotificationKeys.FRIEND_ID_KEY]?.toInt() ?: 0)
                val message = (data[NotificationKeys.MESSAGE_KEY]).toString()
                val time = (data[NotificationKeys.TIME_KEY]).toString()

                GlobalScope.launch {
                    events.emit(
                        NotificationData(
                            id = id,
                            friendId = friendId,
                            message = message,
                            time = time,
                        )
                    )
                }
            }
        }
    }

    override fun onNewToken(token: String) {
        Log.v("DEVFALAHMESSAGE", token)
    }

    companion object{
        val events = MutableSharedFlow<NotificationData>()
    }

}
