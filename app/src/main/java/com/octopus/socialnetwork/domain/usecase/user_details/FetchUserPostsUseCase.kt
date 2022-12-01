package com.octopus.socialnetwork.domain.usecase.user_details

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.user_details.asUserPosts
import com.octopus.socialnetwork.domain.model.user_details.UserPosts
import javax.inject.Inject

class FetchUserPostsUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
) {
    suspend operator fun invoke(uguid: Int, guid: Int) : UserPosts {
        return socialRepository.getUserPosts(uguid, guid).asUserPosts()
    }
}