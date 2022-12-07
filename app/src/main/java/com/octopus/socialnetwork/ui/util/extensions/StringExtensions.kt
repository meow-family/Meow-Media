package com.octopus.socialnetwork.ui.util.extensions

import com.octopus.socialnetwork.R
import com.octopus.socialnetwork.ui.util.Constants

val NotificationsTypes = Constants.NotificationsTypes

fun String.setNotificationsType() : Int {
    return when(this){
        NotificationsTypes.LIKE_POST -> R.string.liked_your_post
        NotificationsTypes.COMMENTS_POST -> R.string.add_comments_to_your_post
        NotificationsTypes.GROUP_JOIN_REQUEST -> R.string.asked_to_join_group
        NotificationsTypes.LIKE_ANNOTATION_COMMENTS_POST -> R.string.liked_your_comment
        NotificationsTypes.WALL_FRIENDS_TAG -> R.string.mentioned_you_in_comment
        NotificationsTypes.OSSNPOKE_POKE -> R.string.poked_you
        else -> R.string.started_following_you
    }
}