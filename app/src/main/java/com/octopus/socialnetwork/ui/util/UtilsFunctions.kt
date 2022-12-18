package com.octopus.socialnetwork.ui.util

import androidx.navigation.NavController
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
                    notification.notificationDetails.subjectId,
                    notification.notificationDetails.ownerId
                )

            notificationsTypes.LIKE_ANNOTATION_COMMENTS_POST ->
                navController.navigateToCommentsScreen(
                    notification.notificationDetails.subjectId,
                    notification.notificationDetails.type
                )

            notificationsTypes.COMMENTS_POST ->
                navController.navigateToCommentsScreen(
                    notification.notificationDetails.subjectId,
                    notification.notificationDetails.type
                )

            else ->
                navController.navigateToPostScreen(
                    notification.notificationDetails.subjectId,
                    notification.notificationDetails.ownerId
                )
        }
    }
}
