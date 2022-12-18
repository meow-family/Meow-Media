package com.octopus.socialnetwork.ui.util.extensions

import androidx.core.text.HtmlCompat
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.util.Constants
import java.text.SimpleDateFormat
import java.util.*

val notificationsTypes = Constants.NotificationsTypes

fun String.setNotificationsTitle() : Int {
    return when(this){
        notificationsTypes.LIKE_POST -> R.string.liked_your_post
        notificationsTypes.COMMENTS_POST -> R.string.add_comments_to_your_post
        notificationsTypes.LIKE_ANNOTATION_COMMENTS_POST -> R.string.liked_your_comment
        notificationsTypes.WALL_FRIENDS_TAG -> R.string.mentioned_you_in_comment
        notificationsTypes.LIKE_ENTITY_FILE_OSSN_APHOTO -> R.string.liked_your_photo_in_album
        notificationsTypes.COMMENTS_ENTITY_FILE_OSSN_APHOTO -> R.string.add_comments_to_your_photo_in_album
        else -> R.string.unknown_notification
    }
}

fun setBadgeCountValue(badgeCount: Int):
        String = if (badgeCount in 1..9) badgeCount.toString() else "+9"


fun String.removeHtmlEncoding(): String {
    return HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_COMPACT).toString()
}

fun Long?.toFormattedDate(pattern: String = "yyyy-MM-dd HH:mm a", locale: String = "en"): Date {
    val dateFormat = SimpleDateFormat(pattern, Locale(locale))
    return this?.let { dateFormat.parse(dateFormat.format(Date(it * 1000))) } ?: Date()
}