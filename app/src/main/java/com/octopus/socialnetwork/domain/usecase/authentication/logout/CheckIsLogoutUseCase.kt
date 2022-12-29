package com.octopus.socialnetwork.domain.usecase.authentication.logout

import com.octopus.socialnetwork.domain.usecase.authentication.FetchUserIdUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CheckIsLogoutUseCase @Inject constructor(
    private val fetchUserId: FetchUserIdUseCase,
) {
    suspend operator fun invoke(): Flow<Boolean> {
        return flow {
            fetchUserId().collect { userId ->
                emit(userId == -1)
            }

        }
    }
}