package com.octopus.socialnetwork.domain.usecase.user.user_details

import com.octopus.socialnetwork.data.repository.social.SocialRepository
import com.octopus.socialnetwork.domain.usecase.authentication.FetchUserIdUseCase
import javax.inject.Inject

class InsertMyProfileDetailsLocallyUseCase @Inject constructor(
    private val socialRepository: SocialRepository,
    private val fetchUserIdUseCase: FetchUserIdUseCase,
) {
    suspend operator fun invoke() {
        return socialRepository.insertProfileDetails(fetchUserIdUseCase())
    }
}