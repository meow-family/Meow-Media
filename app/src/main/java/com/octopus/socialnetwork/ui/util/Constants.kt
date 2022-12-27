package com.octopus.socialnetwork.ui.util

object Constants {
    const val REGEX_EMAIL_VALIDATION = "^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*\$"
    const val ITEMS_PER_PAGE = 10
    const val LIKE_TYPE = "annotation"
    const val COMMENT_TYPE = "post"

    object NotificationsTypes {
        const val LIKE_POST = "like:post"
        const val COMMENTS_POST = "comments:post"
        const val LIKE_ANNOTATION_COMMENTS_POST = "like:annotation:comments:post"
        const val WALL_FRIENDS_TAG = "wall:friends:tag"
        const val LIKE_ENTITY_FILE_OSSN_APHOTO = "like:entity:file:ossn:aphoto"
        const val COMMENTS_ENTITY_FILE_OSSN_APHOTO = "comments:entity:file:ossn:aphoto"

    }

    val NOTIFICATIONS_TYPES_List = with(NotificationsTypes){
        listOf(
            LIKE_POST, COMMENTS_POST, LIKE_ANNOTATION_COMMENTS_POST,
            WALL_FRIENDS_TAG, LIKE_ENTITY_FILE_OSSN_APHOTO,
            COMMENTS_ENTITY_FILE_OSSN_APHOTO
        )
    }

    const val INVALID_USER = 0
}