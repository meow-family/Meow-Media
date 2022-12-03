package com.octopus.socialnetwork.domain.usecase.viewpost

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.like.asLike
import com.octopus.socialnetwork.domain.model.like.Like
import com.octopus.socialnetwork.domain.model.post.Post
import javax.inject.Inject

class FetchPostDetailsUseCase @Inject constructor(
    private val socialRepository: SocialRepository
) {
    suspend operator fun invoke(postId: Int, userId: Int): Post {
        return socialRepository.viewPost(postId, userId)
    }
}