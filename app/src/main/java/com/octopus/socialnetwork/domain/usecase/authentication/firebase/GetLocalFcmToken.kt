package com.octopus.socialnetwork.domain.usecase.authentication.firebase

import com.octopus.socialnetwork.data.repository.authentication.AuthenticationRepository
import javax.inject.Inject


class GetLocalFcmToken @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
) {
    suspend operator fun invoke(): String? {
        return authenticationRepository.getLocalFcmToken()
    }
}