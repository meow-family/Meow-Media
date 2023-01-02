package com.octopus.socialnetwork.domain.mapper.comments

import com.octopus.socialnetwork.data.remote.response.dto.comment.CommentAddDto
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

fun CommentAddDto.toComment(): Comment {
    return Comment(
        comment = comment?.commentsPost?: "",
        commentOwnerId = comment?.commentOwnerId ?: 0,
        isLikedByUser = comment?.isLikedByUser ?: false,
        timeCreated = comment?.timeCreated.toFormattedDate(),
        totalLikes = comment?.totalLikes ?: 0,
        fullName = user?.fullName ?: "",
        username = user?.username ?: "",
        avatar = user?.avatar?.small ?: "",
        commentId = comment?.commentId ?: 0,
    )
}