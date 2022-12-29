package com.octopus.socialnetwork.domain.usecase.authentication.logout

import com.octopus.socialnetwork.data.repository.authentication.AuthenticationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckIsLoggedInUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
) {
    operator fun invoke(): Flow<Boolean> {
        return authenticationRepository.getLoginState()
    }
}