package com.octopus.socialnetwork.domain.usecase.post

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.usecase.authentication.FetchUserIdUseCase
import javax.inject.Inject

class DeletePostUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
    private val fetchUserIdUseCase: FetchUserIdUseCase
) {
    suspend operator fun invoke(postId: Int) {
        socialRepository.deletePost(postId, fetchUserIdUseCase())
        socialRepository.deletePostFromDB(postId)
    }

}