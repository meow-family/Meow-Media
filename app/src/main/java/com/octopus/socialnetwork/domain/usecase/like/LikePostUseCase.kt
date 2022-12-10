package com.octopus.socialnetwork.domain.usecase.like

import javax.inject.Inject

class LikePostUseCase @Inject constructor(
    private val likeUseCase: LikeUseCase,
    private val unlikeUseCase: UnlikeUseCase,
) {
    suspend operator fun invoke(postId: Int, isLiked: Boolean): Int? {
        return if (isLiked) {
            unlikeUseCase(userId = 23, postId = postId)
        } else {
            likeUseCase(userId = 23, postId = postId)
        }
    }
}