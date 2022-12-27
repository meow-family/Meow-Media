package com.octopus.socialnetwork.domain.usecase.authentication.firebase

import com.octopus.socialnetwork.data.repository.authentication.AuthenticationRepository
import javax.inject.Inject


class GetUserTokenUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) {
    suspend operator fun invoke(userId: String): String? {
        return authenticationRepository.getFirebaseUser(userId)?.token
    }
}