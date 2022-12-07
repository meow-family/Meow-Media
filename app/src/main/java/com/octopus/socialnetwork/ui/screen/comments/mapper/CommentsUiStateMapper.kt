package com.octopus.socialnetwork.ui.screen.comments.mapper

import com.octopus.socialnetwork.domain.model.comment.Comment
import com.octopus.socialnetwork.ui.screen.comments.uistate.CommentDetailsUiState

fun Comment.toCommentDetailsUiState(): CommentDetailsUiState {
    return CommentDetailsUiState(
        comment = comment,
        commentOwnerId = commentOwnerId,
        isLikedByUser = isLikedByUser,
        timeCreated = timeCreated,
        likeCounter = totalLikes,
        fullName = fullName,
        userName = username,
        userAvatar = avatar,
    )
}