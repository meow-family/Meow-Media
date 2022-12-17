package com.octopus.socialnetwork.ui.util.extensions

import androidx.core.text.HtmlCompat
import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.util.Constants

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

fun String?.changeTypeToBoolean(): Boolean = this != null

fun String.toCleanTextFromHtml(): String {
    return HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_COMPACT).toString()
}