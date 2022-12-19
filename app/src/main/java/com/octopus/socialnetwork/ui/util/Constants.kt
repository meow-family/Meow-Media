package com.octopus.socialnetwork.ui.util

object Constants {
    const val REGEX_EMAIL_VALIDATION = "^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*\$"
    const val ITEMS_PER_PAGE = 10

    object NotificationsTypes {
        const val LIKE_POST = "like:post"
        const val COMMENTS_POST = "comments:post"
        const val GROUP_JOIN_REQUEST = "group:joinrequest"
        const val LIKE_ANNOTATION_COMMENTS_POST = "like:annotation:comments:post"
        const val WALL_FRIENDS_TAG = "wall:friends:tag"
        const val OSSNPOKE_POKE = "ossnpoke:poke"
        const val LIKE_ENTITY_FILE_OSSN_APHOTO = "like:entity:file:ossn:aphoto"
        const val COMMENTS_ENTITY_FILE_OSSN_APHOTO = "comments:entity:file:ossn:aphoto"

    }
}