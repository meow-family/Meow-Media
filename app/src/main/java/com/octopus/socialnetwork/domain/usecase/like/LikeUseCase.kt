package com.octopus.socialnetwork.domain.usecase.like

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import javax.inject.Inject

class LikeUseCase @Inject constructor(
    private val socialRepository: SocialRepository
) {
    suspend operator fun invoke(userId: Int, postId: Int, typeContent: String = "post"): Int?{
        return socialRepository.like(userId, postId, typeContent).count
    }
}