package com.octopus.socialnetwork.domain.usecase.like

import javax.inject.Inject

class LikeToggleUseCase @Inject constructor(
    private val likeUseCase: LikeUseCase,
    private val unlikeUseCase: UnlikeUseCase,
) {
    suspend operator fun invoke(contentId: Int, isLiked: Boolean,contentType: String): Int? {
        return if (isLiked) {
            unlikeUseCase(userId = 23, postId = contentId, contentType = contentType)
        } else {
            likeUseCase(userId = 23, contentId = contentId, contentType = contentType)
        }
    }
}