package com.octopus.socialnetwork.data.mapper

import com.octopus.socialnetwork.data.local.entity.CommentEntity
import com.octopus.socialnetwork.data.remote.response.dto.comment.CommentDetails
import com.octopus.socialnetwork.data.utils.removeHtmlEncoding
import com.octopus.socialnetwork.data.utils.toFormattedDate

fun CommentDetails.toCommentEntity(): CommentEntity {
    return CommentEntity(
        commentId = commentId ?: 0,
        fullName = user?.fullName ?: "",
        userName = user?.username ?: "",
        userAvatar = user?.avatar?.large ?: "",
        comment = commentsPost?.removeHtmlEncoding() ?: "",
        commentOwnerId = commentOwnerId ?: 0,
        isLikedByUser = isLikedByUser ?: false,
        timeCreated = timeCreated ?: 0,
        totalLikes = totalLikes ?: 0,
        postId = subjectId ?: 0

    )
}