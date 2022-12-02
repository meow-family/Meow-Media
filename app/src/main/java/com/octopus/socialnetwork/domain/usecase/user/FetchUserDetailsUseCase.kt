package com.octopus.socialnetwork.domain.usecase.user

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.user.asUserDetails
import com.octopus.socialnetwork.domain.model.user.UserDetails
import javax.inject.Inject

class FetchUserDetailsUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
) {
    suspend operator fun invoke(guid: Int) : UserDetails {
        return socialRepository.getUserDetails(guid).asUserDetails()
    }

}