package com.octopus.socialnetwork.domain.usecase.authentication.firebase

import com.octopus.socialnetwork.data.repository.authentication.AuthenticationRepository
import javax.inject.Inject

class UpdateUserTokenUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) {
    suspend operator fun invoke(userId: String, token: String) {
        return authenticationRepository.updateUserToken(userId, token)
    }
}