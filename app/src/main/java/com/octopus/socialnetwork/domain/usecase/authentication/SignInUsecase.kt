package com.octopus.socialnetwork.domain.usecase.authentication

import com.octopus.socialnetwork.data.repository.authentication.AuthenticationRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val signInRepository: AuthenticationRepository,
) {
    suspend operator fun invoke(username: String, password: String): LoginResponse {
        val validateRequestWithLogin = signInRepository.login(username, password)
        return if (validateRequestWithLogin.code == "100") {
            LoginResponse.Success
        } else {
            val body = validateRequestWithLogin.result
            LoginResponse.Failure(
                validateRequestWithLogin.message.toString(),
                validateRequestWithLogin.code.toString()
            )
        }
    }

}