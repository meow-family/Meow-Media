package com.octopus.socialnetwork.domain.usecase.authentication.logout

import com.octopus.socialnetwork.data.repository.authentication.AuthenticationRepository
import com.octopus.socialnetwork.domain.usecase.authentication.FetchUserIdUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    val authenticationRepository: AuthenticationRepository,
) {
    suspend operator fun invoke() {
        authenticationRepository.deleteUserId()
    }
}