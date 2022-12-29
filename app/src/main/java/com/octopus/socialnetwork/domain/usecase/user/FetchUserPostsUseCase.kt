package com.octopus.socialnetwork.domain.usecase.user

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.user.toPosts
import com.octopus.socialnetwork.domain.model.user.Posts
import com.octopus.socialnetwork.domain.usecase.authentication.FetchUserIdUseCase
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class FetchUserPostsUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
    private val fetchUserIdUseCase: FetchUserIdUseCase,
) {
    suspend operator fun invoke(visitedUserId: Int) : Posts {
        return socialRepository.getUserPosts(visitedUserId, fetchUserIdUseCase().first()).toPosts()
    }
}