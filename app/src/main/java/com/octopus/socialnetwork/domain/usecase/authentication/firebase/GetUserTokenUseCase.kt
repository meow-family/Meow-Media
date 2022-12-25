package com.octopus.socialnetwork.domain.usecase.authentication.firebase

import com.octopus.socialnetwork.data.repository.firebase.FirebaseRepository
import javax.inject.Inject


class GetUserTokenUseCase @Inject constructor(
    private val firebase: FirebaseRepository,
) {
    suspend operator fun invoke(userId: String): String? {
        return firebase.getUser(userId)?.token
    }
}