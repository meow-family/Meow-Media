package com.octopus.socialnetwork.domain.usecase.user

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.user.asUserPosts
import com.octopus.socialnetwork.domain.model.user.UserPosts
import javax.inject.Inject

class FetchUserPostsUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
) {
    suspend operator fun invoke(uguid: Int, guid: Int) : UserPosts {
        return socialRepository.getUserPosts(uguid, guid).asUserPosts()
    }
}