package com.octopus.socialnetwork.domain.usecase.like

import javax.inject.Inject

class UpdateCommentLikeUseCase @Inject constructor(
    private val likeCommentUseCase: LikeCommentUseCase,
    private val unlikeCommentUseCase: UnlikeCommentUseCase,
) {
    suspend operator fun invoke(commentId: Int, isLiked: Boolean): Int? {
        return if (isLiked) {
            unlikeCommentUseCase(userId = 16, commentId = commentId)
        } else {
            likeCommentUseCase(userId = 16, commentId = commentId)
        }
    }
}