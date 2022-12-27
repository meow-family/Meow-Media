package com.octopus.socialnetwork.domain.usecase.authentication.login

import com.octopus.socialnetwork.data.remote.response.dto.auth.LoginDto
import com.octopus.socialnetwork.data.repository.authentication.AuthenticationRepository
import com.octopus.socialnetwork.domain.usecase.authentication.firebase.GetLocalFcmToken
import com.octopus.socialnetwork.domain.usecase.authentication.firebase.UpdateUserTokenUseCase
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val updateUserTokenUseCase: UpdateUserTokenUseCase,
    private val getLocalFcmToken: GetLocalFcmToken,
) {
    suspend operator fun invoke(username: String, password: String): LoginDto {
        val user = authenticationRepository.login(username, password)
        if (user.id != null) {
            getLocalFcmToken()?.let { updateUserTokenUseCase(user.id.toString(), it) }
        }
        return user
    }
}