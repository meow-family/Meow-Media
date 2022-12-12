package com.octopus.socialnetwork.domain.usecase.user

import com.octopus.socialnetwork.data.repository.authentication.AuthenticationRepository
import javax.inject.Inject

class FetchUserIdUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
) {
    operator fun invoke(): Int {
        return authenticationRepository.getUserId() ?:23
    }
}