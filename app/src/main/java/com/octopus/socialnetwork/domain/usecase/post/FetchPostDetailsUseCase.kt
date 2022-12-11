package com.octopus.socialnetwork.domain.usecase.post

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.posts.toPost
import com.octopus.socialnetwork.domain.model.post.Post
import javax.inject.Inject

class FetchPostDetailsUseCase @Inject constructor(
    private val socialRepository: SocialRepository
) {
    suspend operator fun invoke(postId: Int, currentUserId: Int): Post {
        return socialRepository.viewPost(postId, currentUserId).toPost()
    }
}