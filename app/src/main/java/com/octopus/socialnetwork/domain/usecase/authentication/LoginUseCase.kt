package com.octopus.socialnetwork.domain.usecase.authentication

import com.octopus.socialnetwork.data.remote.response.dto.auth.AuthResponse
import com.octopus.socialnetwork.data.repository.authentication.AuthenticationRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
) {
    suspend operator fun invoke(username: String, password: String): AuthResponse {
        return authenticationRepository.login(username, password)
    }
}