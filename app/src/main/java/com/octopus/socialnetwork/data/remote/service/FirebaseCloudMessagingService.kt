package com.octopus.socialnetwork.data.remote.service

import android.util.Log
import com.google.firebase.messaging.EnhancedIntentService
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.octopus.socialnetwork.data.remote.response.dto.messages.NotificationData
import com.octopus.socialnetwork.data.remote.response.dto.messages.NotificationKeys
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class FirebaseCloudMessagingService @Inject constructor(): FirebaseMessagingService(){

    override fun onMessageReceived(message: RemoteMessage) {

            Log.d("TESTING", "From: ${message.rawData}")
        if (message.data.isNotEmpty()) {
            Log.d("TESTING", "Message data payload: ${message.data}")
        }
        message.data.let { data ->
            Log.i("TESTING","onMessageReceived $data")
            if (data.isNotEmpty()) {
                val id = (data[NotificationKeys.ID_KEY]?.toInt() ?: 0)
                val friendId = (data[NotificationKeys.FRIEND_ID_KEY]?.toInt() ?: 0)
                val message = (data[NotificationKeys.MESSAGE_KEY]).toString()
                val time = (data[NotificationKeys.TIME_KEY]).toString()

                GlobalScope.launch(Dispatchers.IO) {
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
        Log.v("TESTING", "onNewToken $token")

    }

    companion object{
        val events = MutableSharedFlow<NotificationData>()
    }

}
