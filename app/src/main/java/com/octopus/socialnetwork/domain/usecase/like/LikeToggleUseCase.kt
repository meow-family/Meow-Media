package com.octopus.socialnetwork.domain.usecase.like

import com.octopus.socialnetwork.domain.usecase.user.FetchUserIdUseCase
import javax.inject.Inject

class LikeToggleUseCase @Inject constructor(
    private val likeUseCase: LikeUseCase,
    private val unlikeUseCase: UnlikeUseCase,
    private val fetchUserIdUseCase: FetchUserIdUseCase,
) {
    suspend operator fun invoke(contentId: Int, isLiked: Boolean,contentType: String): Int? {
        val userId = fetchUserIdUseCase()
        return if (isLiked) {
            unlikeUseCase(userId = userId, postId = contentId, contentType = contentType)
        } else {
            likeUseCase(userId = userId, contentId = contentId, contentType = contentType)
        }
    }
}