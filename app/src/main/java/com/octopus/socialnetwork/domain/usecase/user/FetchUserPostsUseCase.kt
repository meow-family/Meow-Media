package com.octopus.socialnetwork.domain.usecase.user

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.user.toUserPosts
import com.octopus.socialnetwork.domain.model.user.UserPosts
import javax.inject.Inject

class FetchUserPostsUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
    private val fetchUserIdUseCase: FetchUserIdUseCase,
) {
    suspend operator fun invoke(visitedUserId: Int) : UserPosts {
        return socialRepository.getUserPosts(visitedUserId, fetchUserIdUseCase()).toUserPosts()
    }
}