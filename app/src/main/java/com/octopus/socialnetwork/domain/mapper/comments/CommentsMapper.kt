package com.octopus.socialnetwork.domain.mapper.comments

import com.octopus.socialnetwork.data.remote.response.dto.comment.CommentDto
import com.octopus.socialnetwork.domain.model.comment.Comment
import com.octopus.socialnetwork.domain.utils.toFormattedDate

fun CommentDto.toComment(): Comment {
    return Comment(
        comment = commentsPost?: "",
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