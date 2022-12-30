package com.octopus.socialnetwork.domain.usecase.user.user_details

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.user.toUser
import com.octopus.socialnetwork.domain.model.user.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FetchProfileDetailsUseCase @Inject constructor(
    private val socialRepository: SocialRepository
) {
    suspend operator fun invoke() : Flow<User>{
        return socialRepository.getProfileDetails().map { it.toUser() }
    }
}