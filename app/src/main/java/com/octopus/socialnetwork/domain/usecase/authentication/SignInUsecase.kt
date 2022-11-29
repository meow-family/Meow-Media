package com.octopus.socialnetwork.domain.usecase.authentication

import com.octopus.socialnetwork.data.repository.authentication_repository.SignInRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val signInRepository: SignInRepository,
) {
    suspend operator fun invoke(username: String, password: String): LoginResponse {
        val validateRequestWithLogin = signInRepository.login(username, password)
        return if (validateRequestWithLogin.isSuccessful) {
            LoginResponse.Success
        } else {
           val body =  validateRequestWithLogin.body()
            LoginResponse.Failure(body?.message!!, body.code!!)
        }
    }

}