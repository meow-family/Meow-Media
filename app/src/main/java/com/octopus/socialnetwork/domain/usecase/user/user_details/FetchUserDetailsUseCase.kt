package com.octopus.socialnetwork.domain.usecase.user.user_details

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.user.toUser
import com.octopus.socialnetwork.domain.model.user.User
import javax.inject.Inject

class FetchUserDetailsUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
) {
    suspend operator fun invoke(ownerId: Int) : User {
        return socialRepository.getUserDetails(ownerId).toUser()
    }

}