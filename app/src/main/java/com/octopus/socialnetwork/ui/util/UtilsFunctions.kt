
import androidx.navigation.NavController
import com.octopus.socialnetwork.ui.screen.comments.navigateToCommentsScreen
import com.octopus.socialnetwork.ui.screen.notifications.state.NotificationItemsUiState
import com.octopus.socialnetwork.ui.screen.post.navigateToPostScreen
import com.octopus.socialnetwork.ui.util.Constants.NOTIFICATIONS_TYPES_List
import com.octopus.socialnetwork.ui.util.extensions.notificationsTypes
import java.text.SimpleDateFormat
import java.util.*

fun convertLongToDate(timeCreated: Long?) : Date {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm a", Locale("en"))
    return if(timeCreated != null)
        dateFormat.parse(dateFormat.format(Date(timeCreated * 1000)))!!
    else Date()
}

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
