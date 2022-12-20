package com.octopus.socialnetwork.domain.usecase.user

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.model.user.UserDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchUserDetailsUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
) {
    suspend operator fun invoke(ownerId: Int): Flow<UserDetails> {
        return socialRepository.getUserDetails(ownerId)
    }

}