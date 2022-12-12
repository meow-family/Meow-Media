package com.octopus.socialnetwork.domain.usecase.like

import com.octopus.socialnetwork.domain.usecase.user.FetchUserIdUseCase
import javax.inject.Inject

class UpdateLikeUseCase @Inject constructor(
    private val likeUseCase: LikeUseCase,
    private val unlikeUseCase: UnlikeUseCase,
    private val fetchUserIdUseCase: FetchUserIdUseCase,
) {
    suspend operator fun invoke(postId: Int, isLiked: Boolean): Int? {
        val userId =fetchUserIdUseCase()
        return if (isLiked) {
            unlikeUseCase(userId = userId, postId = postId)
        } else {
            likeUseCase(userId = userId, postId = postId)
        }
    }
}