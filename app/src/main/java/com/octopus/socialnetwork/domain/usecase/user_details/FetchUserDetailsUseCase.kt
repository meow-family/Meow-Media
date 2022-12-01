package com.octopus.socialnetwork.domain.usecase.user_details

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.mapper.user_details.asUserDetails
import com.octopus.socialnetwork.domain.model.user_details.UserDetails
import javax.inject.Inject

class FetchUserDetailsUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
) {
    suspend operator fun invoke(guid: Int) : UserDetails {
        return socialRepository.getUserDetails(guid).asUserDetails()
    }

}