package com.octopus.socialnetwork.domain.usecase.authentication

import com.octopus.socialnetwork.data.remote.response.dto.auth.AuthResponse
import com.octopus.socialnetwork.data.repository.authentication.AuthenticationRepository
import com.octopus.socialnetwork.domain.usecase.authentication.validation_use_case.ValidateUseCase
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val validateUseCase: ValidateUseCase,
    private val authenticationRepository: AuthenticationRepository,
){
    suspend operator fun invoke(username: String, password: String): AuthResponse? {
        val isValidInput = validateUseCase(username, password)
        return if (isValidInput) {
            authenticationRepository.login(username,password)
        } else {
            null
        }
    }
}