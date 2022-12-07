package com.octopus.socialnetwork.domain.usecase.authentication

import com.octopus.socialnetwork.data.repository.authentication.AuthenticationRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val signInRepository: AuthenticationRepository,
) {
    suspend operator fun invoke(params: Params): LoginResponse {

        val response = signInRepository.register(params = params)

        return if (response.code == "100") {
            LoginResponse.Success
        } else {
            val body = response.result
            LoginResponse.Failure(
                response.message.toString(),
                response.code.toString()
            )
        }
    }

    data class Params(
        var firstName: String,
        var lastName: String,
        var email: String,
        var reEmail: String,
        var gender: String,
        var birthDate: String,
        var userName: String,
        var password: String,
    )

}