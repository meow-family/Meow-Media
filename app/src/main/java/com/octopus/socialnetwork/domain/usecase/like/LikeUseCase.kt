package com.octopus.socialnetwork.domain.usecase.like

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.like.asLike
import com.octopus.socialnetwork.domain.model.like.Like
import javax.inject.Inject

class LikeUseCase @Inject constructor(
    private val socialRepository: SocialRepository
) {
    suspend operator fun invoke(userId: Int, contentId: Int, typeContent: String): Like {
        return socialRepository.like(userId, contentId, typeContent).payload.asLike()
    }
}