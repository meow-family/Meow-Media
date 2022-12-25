package com.octopus.socialnetwork.ui.util

import android.content.Context
import android.media.MediaPlayer
import androidx.navigation.NavController
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.screen.comments.navigateToCommentsScreen
import com.octopus.socialnetwork.ui.screen.notifications.state.NotificationItemsUiState
import com.octopus.socialnetwork.ui.screen.post.navigateToPostScreen
import com.octopus.socialnetwork.ui.util.Constants.NOTIFICATIONS_TYPES_List
import com.octopus.socialnetwork.ui.util.extensions.notificationsTypes


fun isNotificationType(type: String) : Boolean = NOTIFICATIONS_TYPES_List.contains(type)

fun onClickNotification(
    type: String,
    navController: NavController,
    notification: NotificationItemsUiState
) {
    if(isNotificationType(type)){
        when(type) {
            notificationsTypes.LIKE_POST ->
                navController.navigateToPostScreen(
                    notification.subjectId,
                    notification.ownerId
                )

            notificationsTypes.LIKE_ANNOTATION_COMMENTS_POST ->
                navController.navigateToCommentsScreen(
                    notification.subjectId,
                    notification.type
                )

            notificationsTypes.COMMENTS_POST ->
                navController.navigateToCommentsScreen(
                    notification.subjectId,
                    notification.type
                )

            else ->
                navController.navigateToPostScreen(
                    notification.subjectId,
                    notification.ownerId
                )
        }
    }
}

fun playMeowSound(isMeowed: Boolean, context: Context) {
    if(!isMeowed){
        val mp: MediaPlayer = MediaPlayer.create(context, R.raw.meow_sound)
        mp.start()
    }
}