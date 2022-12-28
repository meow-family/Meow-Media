package com.octopus.socialnetwork.domain.usecase.like

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.usecase.authentication.FetchUserIdUseCase
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class ToggleLikeUseCase @Inject constructor(
    private val likeUseCase: LikeUseCase,
    private val unlikeUseCase: UnlikeUseCase,
    private val fetchUserIdUseCase: FetchUserIdUseCase,
    private val socialRepository: SocialRepository,
) {
    suspend operator fun invoke(contentId: Int, totalLikes: Int, isLiked: Boolean, contentType: String): Int? {
        val userId = fetchUserIdUseCase().first()
        return if (isLiked) {
            // LIKED -> UNLIKE
            socialRepository.updatePostLikeStatusLocally(contentId, false, totalLikes.minus(1))
            unlikeUseCase(userId, contentId, contentType)
        } else {
            // UNLIKED -> LIKE
            socialRepository.updatePostLikeStatusLocally(contentId, true, totalLikes.plus(1))
            likeUseCase(userId, contentId, contentType)
        }
    }
}