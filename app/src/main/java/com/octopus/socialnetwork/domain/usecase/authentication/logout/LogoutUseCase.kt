package com.octopus.socialnetwork.domain.usecase.authentication.logout

import com.octopus.socialnetwork.data.repository.authentication.AuthenticationRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    val authenticationRepository: AuthenticationRepository,
) {
    suspend operator fun invoke() {
        authenticationRepository.deleteUserId()
    }
}