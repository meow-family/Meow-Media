package com.octopus.socialnetwork.domain.usecase.authentication.validation_use_case

import com.octopus.socialnetwork.data.repository.authentication.AuthenticationRepository
import com.octopus.socialnetwork.domain.usecase.authentication.LoginResponse
import javax.inject.Inject

class ValidateUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) {
    suspend operator fun invoke(username: String, password: String) : LoginResponse {

        val loginResponse = authenticationRepository.login(username, password)
        return if (loginResponse.code == "100") {
            LoginResponse.Success
        } else {
            LoginResponse.Failure(
                loginResponse.code.toString(),
                loginResponse.message.toString()
            )
        }
    }
}
