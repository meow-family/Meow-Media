package com.octopus.socialnetwork.domain.usecase.like

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import javax.inject.Inject

class LikeCommentUseCase @Inject constructor(
    private val socialRepository: SocialRepository
) {
    suspend operator fun invoke(userId: Int, commentId: Int, typeContent: String = "comment"): Int?{
        return socialRepository.like(userId, commentId, typeContent).count
    }
}