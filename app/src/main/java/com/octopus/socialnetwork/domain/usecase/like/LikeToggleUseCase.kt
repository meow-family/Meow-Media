package com.octopus.socialnetwork.domain.usecase.like

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.usecase.user.FetchUserIdUseCase
import javax.inject.Inject

class LikeToggleUseCase @Inject constructor(
    private val likeUseCase: LikeUseCase,
    private val unlikeUseCase: UnlikeUseCase,
    private val fetchUserIdUseCase: FetchUserIdUseCase,
    private val socialRepository: SocialRepository,
) {
    suspend operator fun invoke(contentId: Int, totalLikes: Int, isLiked: Boolean, contentType: String): Int? {
        return if (isLiked) {
            // LIKED -> UNLIKE
            socialRepository.updatePostLikeStatusLocally(contentId, false, totalLikes.minus(1))
            val userId = fetchUserIdUseCase()
            unlikeUseCase(userId, contentId, contentType)
        } else {
            // UNLIKED -> LIKE
            socialRepository.updatePostLikeStatusLocally(contentId, true, totalLikes.plus(1))
            val userId = fetchUserIdUseCase()
            likeUseCase(userId, contentId, contentType)
        }
    }
}