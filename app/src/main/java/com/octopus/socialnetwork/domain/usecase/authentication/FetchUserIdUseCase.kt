package com.octopus.socialnetwork.domain.usecase.authentication

import com.octopus.socialnetwork.data.repository.authentication.AuthenticationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.asTask
import javax.inject.Inject

class FetchUserIdUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
) {
    operator fun invoke(): Flow<Int> {
        return authenticationRepository.getUserId().map {it ?: -1}
    }
}