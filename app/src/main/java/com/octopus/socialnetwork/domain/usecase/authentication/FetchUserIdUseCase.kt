package com.octopus.socialnetwork.domain.usecase.authentication

import com.octopus.socialnetwork.data.repository.authentication.AuthenticationRepository
import javax.inject.Inject

class FetchUserIdUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
) {
    suspend operator fun invoke(): Int {
        return authenticationRepository.getUserId() ?: -1
    }
}