package com.octopus.socialnetwork.domain.usecase.authentication

import android.util.Log
import com.octopus.socialnetwork.data.repository.authentication.AuthenticationRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val signInRepository: AuthenticationRepository,
) {
    suspend operator fun invoke(
        firstName: String,
        lastName: String,
        email: String,
        reEmail: String,
        gender: String,
        birthDate: String,
        userName: String,
        password: String,
    ): LoginResponse {

        val response = signInRepository.register(
            firstName,
            lastName,
            email,
            reEmail,
            gender,
            birthDate,
            userName,
            password
        )
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

}