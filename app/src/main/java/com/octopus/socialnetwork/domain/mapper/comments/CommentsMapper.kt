package com.octopus.socialnetwork.domain.mapper.comments

import com.octopus.socialnetwork.data.remote.response.dto.comment.CommentDetails
import com.octopus.socialnetwork.domain.model.comment.Comment

fun CommentDetails.toComment(): Comment {
    return Comment(
        comment = commentsPost ?: "",
        commentOwnerId = commentOwnerId ?: 0,
        isLikedByUser = isLikedByUser ?: false,
        timeCreated = timeCreated ?: 0,
        totalLikes = totalLikes ?: 0,
        fullName = user?.fullName ?: "",
        username =user?.username ?: "",
        avatar = user?.avatar?.small ?: "",
        commentId = commentId ?:0,
    )
}