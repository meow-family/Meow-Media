package com.octopus.socialnetwork.domain.usecase.authentication

import com.octopus.socialnetwork.data.remote.response.dto.auth.AuthResponse
import com.octopus.socialnetwork.data.repository.authentication.AuthenticationRepository
import com.octopus.socialnetwork.domain.usecase.user.UpdateUserToken
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val updateUserToken: UpdateUserToken,
    private val getUserToken: GetUserToken,
) {
    suspend operator fun invoke(username: String, password: String): AuthResponse {
        val user = authenticationRepository.login(username, password)
        if (user.id != null) {
            getUserToken()?.let { updateUserToken(user.id.toString(), it) }
        }
        return user
    }
}