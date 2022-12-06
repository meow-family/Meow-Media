package com.octopus.socialnetwork.domain.usecase.post

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.posts.toPost
import com.octopus.socialnetwork.domain.model.post.Post
import javax.inject.Inject

class FetchNewsFeedPostUseCase @Inject constructor(
    private val socialRepository: SocialRepository
) {
    suspend operator fun invoke(currentUserId: Int): List<Post> {
        return socialRepository.viewNewsFeed(currentUserId).map {
            it.toPost()
        }.filter {
            it.image.startsWith("https://")
        }
    }
}