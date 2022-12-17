package com.octopus.socialnetwork.domain.mapper.comments

import com.octopus.socialnetwork.data.remote.response.dto.comment.CommentDetails
import com.octopus.socialnetwork.domain.model.comment.Comment
import com.octopus.socialnetwork.ui.util.extensions.removeHtmlEncoding
import com.octopus.socialnetwork.ui.util.extensions.toFormattedDate

fun CommentDetails.toComment(): Comment {
    return Comment(
        comment = commentsPost?.removeHtmlEncoding() ?: "",
        commentOwnerId = commentOwnerId ?: 0,
        isLikedByUser = isLikedByUser ?: false,
        timeCreated = timeCreated.toFormattedDate(),
        totalLikes = totalLikes ?: 0,
        fullName = user?.fullName ?: "",
        username = user?.username ?: "",
        avatar = user?.avatar?.small ?: "",
        commentId = commentId ?: 0,
    )
}